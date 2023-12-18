package com.hxl.utils.curl;

public class CurlArg {
    private CurlArgsKey curlArgsKey;
    private final String value;
    private final String id;
    public CurlArg(CurlArgsKey curlArgsKey,String id, String value) {
        this.curlArgsKey = curlArgsKey;
        this.value = value;
        this.id=id;
    }

    public String getId() {
        return id;
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
