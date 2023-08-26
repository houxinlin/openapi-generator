package com.hxl.utils.openapi.parameter;

import com.hxl.utils.openapi.Type;

public class OpenApiHeaderParameterNode  extends OpenApiUrlParameter{
    public OpenApiHeaderParameterNode(String name, String description, boolean required, Type type) {
        super(name, description, required, type);
    }
    @Override
    public String getIn() {
        return "header";
    }
}
