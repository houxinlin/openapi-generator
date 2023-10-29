package com.hxl.utils.curl;

public class CurlArg {
    private CurlArgsKey curlArgsKey;
    private String value;

    public CurlArg(CurlArgsKey curlArgsKey, String value) {
        this.curlArgsKey = curlArgsKey;
        this.value = value;
    }

    @Override
    public String toString() {
        return curlArgsKey.getCurlArgKey() + " " + curlArgsKey.getValueResolver().apply(this.value);
    }

    public CurlArgsKey getCurlArgsKey() {
        return curlArgsKey;
    }

    public void setCurlArgsKey(CurlArgsKey curlArgsKey) {
        this.curlArgsKey = curlArgsKey;
    }
}
