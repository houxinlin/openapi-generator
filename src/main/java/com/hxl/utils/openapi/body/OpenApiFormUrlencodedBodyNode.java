package com.hxl.utils.openapi.body;

import com.hxl.utils.openapi.properties.ObjectProperties;

public class OpenApiFormUrlencodedBodyNode extends OpenApiRequestBodyNode {
    @Override
    public String getRequestType() {
        return "application/x-www-form-urlencoded";
    }
    public OpenApiFormUrlencodedBodyNode(ObjectProperties properties) {
        super(properties);
    }
}
