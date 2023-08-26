package com.hxl.utils.openapi.parameter;

import com.hxl.utils.openapi.Type;

public class OpenApiUrlQueryParameter extends OpenApiUrlParameter{
    public OpenApiUrlQueryParameter(String name, String description, boolean required, Type type) {
        super(name, description, required, type);
    }
    @Override
    public String getIn() {
        return "query";
    }
}
