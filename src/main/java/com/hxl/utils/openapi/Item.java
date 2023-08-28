package com.hxl.utils.openapi;

public class Item {
    private final OpenApiPathItemNode openApiPathItemNode;
    public Item(OpenApiPathItemNode openApiPathItemNode) {
        this.openApiPathItemNode =openApiPathItemNode;
    }

    public OpenApiPathItemNode getOpenApiPathItemNode() {
        return openApiPathItemNode;
    }
}
