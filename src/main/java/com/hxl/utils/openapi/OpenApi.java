package com.hxl.utils.openapi;


public class OpenApi extends OpenApiNode {
    public OpenApi() {
        set("openapi", "3.0.0");
        set("paths", new OpenApiNode());
    }
    public OpenApi putPath(OpenApiPathItemNode apiNode) {
        ((OpenApiNode) get("paths")).put(apiNode.getUrlPath(), apiNode.getMethodNode());
        return this;
    }
}
