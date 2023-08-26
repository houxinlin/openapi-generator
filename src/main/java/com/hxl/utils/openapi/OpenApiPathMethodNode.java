package com.hxl.utils.openapi;

public  class OpenApiPathMethodNode extends OpenApiNode {
    public OpenApiPathMethodNode(HttpMethod httpMethod,OpenApiPathMethodDetailNode openApiPathMethodDetailNode) {
        set(httpMethod.toString(),openApiPathMethodDetailNode);
    }
    public static OpenApiPathMethodNode create(HttpMethod httpMethod,OpenApiPathMethodDetailNode openApiPathMethodDetailNode){
        return  new OpenApiPathMethodNode(httpMethod,openApiPathMethodDetailNode);
    }
}
