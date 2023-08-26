package com.hxl.utils.openapi.properties;

public class ArrayProperties  extends BasicProperties{
    private BasicProperties basicProperties;
    public ArrayProperties(BasicProperties basicProperties) {
        this.basicProperties=basicProperties;
    }

    public BasicProperties getBasicProperties() {
        return basicProperties;
    }

    public void setBasicProperties(BasicProperties basicProperties) {
        this.basicProperties = basicProperties;
    }
}
