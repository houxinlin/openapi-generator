package com.hxl.utils.openapi;

import java.util.HashMap;

public class OpenApiNode extends HashMap<String,Object> {
    public OpenApiNode set(String key,Object value){
        this.put(key,value);
        return this;
    }
}
