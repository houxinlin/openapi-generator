package com.hxl.utils.curl;

import com.hxl.utils.openapi.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class Curl {
    private final StringBuffer curlOutputBuffer = new StringBuffer();

    private final List<CurlArg> curlArgs = new ArrayList<>();

    private final String url;

    public Curl(HttpMethod method, String url) {
        this.url = "http://localhost" + url;
        curlOutputBuffer.append("curl -X ").append(method.toString().toUpperCase()).append(" ");
    }

    public void addHeader(String key, String value) {
        curlArgs.add(new CurlArg(CurlArgsKey._H, key + ":" + value));
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
}
