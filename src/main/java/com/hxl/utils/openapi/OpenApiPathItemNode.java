package com.hxl.utils.openapi;

public class OpenApiPathItemNode  extends OpenApiNode{
    private final String urlPath;
    private final OpenApiPathMethodNode methodNode;
    public OpenApiPathItemNode(  String urlPath,OpenApiPathMethodNode methodNode) {
        this.urlPath =urlPath;
        this.methodNode =methodNode;
    }
    public OpenApiPathMethodNode getMethodNode() {
        return methodNode;
    }
    public String getUrlPath() {
        return urlPath;
    }
}
