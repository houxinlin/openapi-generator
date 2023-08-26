package com.hxl.utils.openapi;

public abstract class BasicOpenApiBuilder extends OpenApiBuilder {
    private String url;

    public BasicOpenApiBuilder(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
