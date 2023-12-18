package com.hxl.utils.curl;

import java.util.function.Function;

public enum CurlArgsKey {
    _H("-H", s -> "'"+s+"'"),//add header
    _d("-d", s -> "'"+s+"'"),//add request body
    _G("-G", s -> ""),
    _F("-F", s -> "\""+s+"\""),
    _get_param("--data-urlencode",  s -> "'"+s+"'");
//    _G_d("-G", s -> "-d '"+s+"'");


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
