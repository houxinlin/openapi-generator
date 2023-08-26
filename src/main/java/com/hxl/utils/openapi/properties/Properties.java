package com.hxl.utils.openapi.properties;

public class Properties {
    private String key;
    private PropertiesDescription value;//属性类型

    public Properties(String key, PropertiesDescription value) {
        this.key = key;
        this.value = value;
    }

    public PropertiesDescription getValue() {
        return value;
    }

    public void setValue(PropertiesDescription value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
