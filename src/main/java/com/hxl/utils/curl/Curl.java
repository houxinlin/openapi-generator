package com.hxl.utils.curl;

import com.hxl.utils.openapi.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class Curl {
    private final StringBuffer curlOutputBuffer = new StringBuffer();
    private final List<CurlArg> curlArgs = new ArrayList<>();
    private final List<KeyValue> queryKeyValues = new ArrayList<>();
    private   String normalizeHeader(String headerName) {
        if (headerName == null || headerName.isEmpty()) {
            return headerName;
        }
        StringBuilder normalizedHeader = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : headerName.toLowerCase().toCharArray()) {
            if (c=='-'){
                normalizedHeader.append("-");
                capitalizeNext=true;
                continue;
            }
            normalizedHeader.append(capitalizeNext?Character.toUpperCase(c):c);
            if (capitalizeNext){
                capitalizeNext = false;
            }
        }
        return normalizedHeader.toString();
    }

    private final StringBuffer urlBuffer;
    public Curl(HttpMethod method, String urlBuffer) {
        this.urlBuffer = new StringBuffer(urlBuffer);
        curlOutputBuffer.append("curl -X ").append(method.toString().toUpperCase()).append(" ");
    }
    public void addHeader(String key, String value) {
        key =normalizeHeader(key);
        curlArgs.add(new CurlArg(CurlArgsKey._H, key,key+ ":" + value));
    }
    public void addQuery(String key, String value) {
        curlArgs.add(new CurlArg(CurlArgsKey._G_d,key,key+"="+value));
    }
    public void setRequestBody(String type,String value){
        curlArgs.add(new CurlArg(CurlArgsKey._d,value,value));
    }

    public void addHeaderIfMiss(String key,String value){
        for (CurlArg curlArg : curlArgs) {
            if (curlArg.getCurlArgsKey().equals(CurlArgsKey._H) && key.equalsIgnoreCase(curlArg.getId())){
                return;
            }
        }
        addHeader(key,value);
    }
    @Override
    public String toString() {
        for (CurlArg curlArg : curlArgs) {
            curlOutputBuffer.append(curlArg.toString());
            curlOutputBuffer.append(" ");
        }
        curlOutputBuffer.append(this.urlBuffer);
        return curlOutputBuffer.toString();
    }

    private class KeyValue {
        private String name;
        private String value;

        public KeyValue(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
