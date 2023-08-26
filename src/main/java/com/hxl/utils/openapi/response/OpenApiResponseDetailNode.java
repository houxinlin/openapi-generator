package com.hxl.utils.openapi.response;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.properties.BasicProperties;

import java.util.List;

public class OpenApiResponseDetailNode  extends OpenApiNode {
    public OpenApiResponseDetailNode(String description,String type, List<BasicProperties> properties) {
        set("description",description);
    }
    public OpenApiResponseDetailNode(String description) {
        set("description",description);
    }
}
