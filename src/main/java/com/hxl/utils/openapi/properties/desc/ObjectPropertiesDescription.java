package com.hxl.utils.openapi.properties.desc;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.Type;
import com.hxl.utils.openapi.properties.Properties;

import java.util.List;
public class ObjectPropertiesDescription  extends BasicPropertiesDescription{
    public ObjectPropertiesDescription(List<Properties> leafDescriptions, String description) {
        super(Type.object,description);
        OpenApiNode properties = new OpenApiNode();
        for (Properties leafDescription : leafDescriptions) {
            properties.set(leafDescription.getKey(), leafDescription.getValue());
        }
        set("properties",properties);
    }
}
