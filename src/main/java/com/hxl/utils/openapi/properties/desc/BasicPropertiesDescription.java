package com.hxl.utils.openapi.properties.desc;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.Type;
import com.hxl.utils.openapi.properties.PropertiesDescription;
import com.hxl.utils.openapi.utils.JsonHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BasicPropertiesDescription extends PropertiesDescription {
    public BasicPropertiesDescription(Type type, String description) {
        super(type, description);
    }

    public Object doGetDefaultValue(BasicPropertiesDescription basicPropertiesDescription,BiFunction<BasicPropertiesDescription,OpenApiNode,Object> factory) {
        if (Type.string.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString())) {
            return "";
        }
        if (Type.number.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString()) ||
                Type.integer.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString())) {
            return 0;
        }
        if (Type.object.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString())) {
            if (this.containsKey("properties")) {
                OpenApiNode openApiNode = (OpenApiNode) get("properties");
                return  factory.apply(this,openApiNode);
            }
        }
        return "";
    }

    public Object getDefaultValue(BiFunction<BasicPropertiesDescription,OpenApiNode,Object> factory) {
        return doGetDefaultValue(this,factory);
    }
}
