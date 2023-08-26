package com.hxl.utils.openapi.body;

import com.hxl.utils.openapi.properties.ObjectProperties;

public class OpenApiFormDataRequestBodyNode extends OpenApiRequestBodyNode {
    public OpenApiFormDataRequestBodyNode(ObjectProperties properties) {
        super(properties);
    }

    @Override
    public String getRequestType() {
        return "multipart/form-data";
    }
}
