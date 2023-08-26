package com.hxl.utils.openapi.body;

import com.hxl.utils.openapi.properties.BasicProperties;

public class OpenApiApplicationJSONBodyNode  extends OpenApiRequestBodyNode{
    public OpenApiApplicationJSONBodyNode(BasicProperties properties) {
        super(properties);
    }
    @Override
    public String getRequestType() {
        return "application/json";
    }
}
