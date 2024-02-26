package com.hxl.utils.openapi.utils;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.properties.ArrayProperties;
import com.hxl.utils.openapi.properties.BasicProperties;
import com.hxl.utils.openapi.properties.ObjectProperties;
import com.hxl.utils.openapi.properties.Properties;

import java.util.HashMap;
import java.util.Map;

public class BodyContentUtils {
    public static OpenApiNode getContent(String type, BasicProperties properties) {
        return getContent(type, properties, null);
    }

    public static OpenApiNode getContent(String type, BasicProperties properties, Object exampleValue) {
        OpenApiNode openApi = new OpenApiNode();
        Schema schema = new Schema(properties instanceof ArrayProperties ? "array" : "object", properties);
        OpenApiNode openApiNode = new OpenApiNode();
        if (exampleValue != null) {
            openApiNode.set("example", new Example(exampleValue));
        }
        openApi.set(type, openApiNode.set("schema", schema));
        return openApi;
    }

    public static class Example extends OpenApiNode {
        public Example(Object value) {
            Map<String, Object> content = new HashMap<>();
            content.put("summary", "example");
            content.put("value", value);
            set("1", content);
        }
    }

    public static class Schema extends OpenApiNode {
        private final OpenApiNode propertiesOpenApiNode = new OpenApiNode();

        public Schema(String type, BasicProperties properties) {
            set("type", type);
            applyToParent(this, properties);
        }

        public void applyToParent(OpenApiNode openApiNode, BasicProperties properties) {
            if (properties instanceof ArrayProperties) {
                BasicProperties basicProperties = ((ArrayProperties) properties).getBasicProperties();
                OpenApiNode nextNode = new OpenApiNode();
                openApiNode.set("type", "array");
                openApiNode.set("items", nextNode);
                applyToParent(nextNode, basicProperties);
            }
            if (properties instanceof ObjectProperties) {
                ObjectProperties objectProperties = (ObjectProperties) properties;
                OpenApiNode objectPropertiesMap = new OpenApiNode();
                for (Properties property : objectProperties.getProperties()) {
                    objectPropertiesMap.set(property.getKey(), property.getValue());
                }
                openApiNode.set("properties", objectPropertiesMap);

            }
        }
    }
}
