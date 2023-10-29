package com.hxl.utils.curl;

import java.util.function.Function;

public enum CurlArgsKey {
    _H("-H", s -> "'"+s+"'");//add header

    private final String curlArgKey;
    private final Function<String,String> valueResolver;

    CurlArgsKey(String curlArgKey, Function<String,String> valueResolver) {
        this.curlArgKey = curlArgKey;
        this.valueResolver =valueResolver;
    }

    public String getCurlArgKey() {
        return curlArgKey;
    }

    public Function<String, String> getValueResolver() {
        return valueResolver;
    }
}
