package com.hxl.utils.openapi.parameter;

import com.hxl.utils.openapi.Type;

public class OpenApiUrlPathParameter extends OpenApiUrlParameter {
    public OpenApiUrlPathParameter(String name, String description, boolean required, Type type) {
        super(name, description, required, type);
    }
    @Override
    public String getIn() {
        return "path";
    }
}
