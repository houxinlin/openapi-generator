package com.hxl.utils.openapi.body;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.properties.*;
import com.hxl.utils.openapi.utils.BodyContentUtils;
public abstract class OpenApiRequestBodyNode  extends OpenApiNode {
    public OpenApiRequestBodyNode(BasicProperties properties) {
        set("content", BodyContentUtils.getContent(getRequestType(),properties));
    }
    public abstract String getRequestType();
}
