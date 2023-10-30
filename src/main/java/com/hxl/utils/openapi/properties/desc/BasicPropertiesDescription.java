package com.hxl.utils.openapi.properties.desc;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.Type;
import com.hxl.utils.openapi.properties.PropertiesDescription;
import com.hxl.utils.openapi.utils.JsonHashMap;

import java.util.HashMap;
import java.util.Map;

public class BasicPropertiesDescription extends PropertiesDescription {
    public BasicPropertiesDescription(Type type, String description) {
        super(type, description);
    }

    private Object doGetDefaultValue(BasicPropertiesDescription basicPropertiesDescription) {
        if (Type.string.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString())) {
            return "\"\"";
        }
        if (Type.number.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString())) {
            return 0;
        }
        if (Type.object.toString().equalsIgnoreCase(basicPropertiesDescription.getOrDefault("type", "").toString())) {
            if (this.containsKey("properties")) {
                Map<String,Object> result =new JsonHashMap<>();
                OpenApiNode openApiNode = (OpenApiNode) get("properties");
                for (String key: openApiNode.keySet()) {
                    if (openApiNode.get(key) instanceof BasicPropertiesDescription){
                        result.put(key,doGetDefaultValue(((BasicPropertiesDescription) openApiNode.get(key))));
                    }
                }
                return result;
            }
        }
        return "\"\"";
    }

    public Object getDefaultValue() {
        return doGetDefaultValue(this);
    }
}
