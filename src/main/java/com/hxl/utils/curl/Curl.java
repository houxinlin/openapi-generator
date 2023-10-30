package com.hxl.utils.curl;

import com.hxl.utils.openapi.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class Curl {
    private final StringBuffer curlOutputBuffer = new StringBuffer();

    private final List<CurlArg> curlArgs = new ArrayList<>();
    private final List<KeyValue> queryKeyValues = new ArrayList<>();

    private StringBuffer url;

    public Curl(HttpMethod method, String url) {
        this.url = new StringBuffer("http://localhost").append(url);
        curlOutputBuffer.append("curl -X ").append(method.toString().toUpperCase()).append(" ");
    }

    public void addHeader(String key, String value) {
        curlArgs.add(new CurlArg(CurlArgsKey._H, key + ":" + value));
    }

    public void addQuery(String key, String value) {
        curlArgs.add(new CurlArg(CurlArgsKey._G_d,key+"="+value));
    }

    public void setRequestBody(String type,String value){
        curlArgs.add(new CurlArg(CurlArgsKey._d,value));
    }
    @Override
    public String toString() {
        for (CurlArg curlArg : curlArgs) {
            curlOutputBuffer.append(curlArg.toString());
            curlOutputBuffer.append(" ");
        }

        curlOutputBuffer.append(this.url);
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
