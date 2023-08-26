package com.hxl.utils.openapi.properties;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.Type;

public abstract class PropertiesDescription extends OpenApiNode {
    public PropertiesDescription(Type type, String description) {
        set("type", type.getTargetValue());
        set("description", description);
    }
}
