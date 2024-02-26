package com.hxl.utils.openapi.response;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.properties.BasicProperties;
import com.hxl.utils.openapi.utils.BodyContentUtils;

import java.util.List;

public class OpenApiResponseDetailNode extends OpenApiNode {
    public OpenApiResponseDetailNode(String description, String type, BasicProperties properties, Object example) {
        set("description", description);
        set("content", BodyContentUtils.getContent(type, properties, example));
    }

    public OpenApiResponseDetailNode(String description, String type, BasicProperties properties) {
        set("description", description);
        set("content", BodyContentUtils.getContent(type, properties, null));
    }

    public OpenApiResponseDetailNode(String description) {
        set("description", description);
    }
}
