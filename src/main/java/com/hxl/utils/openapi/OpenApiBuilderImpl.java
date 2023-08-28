package com.hxl.utils.openapi;

public class OpenApiBuilderImpl extends BasicOpenApiBuilder {
    private final HttpMethod httpMethod;
    public OpenApiBuilderImpl(String url, HttpMethod httpMethod) {
        super(url);
        this.httpMethod = httpMethod;
    }
    @Override
    public void addToOpenApi(OpenApi openApi) {
        openApi.putPath(new OpenApiPathItemNode(getUrl(), new OpenApiPathMethodNode(httpMethod, getOpenApiPathMethodDetailNode())));
//        return new OpenApi().putPath(new OpenApiPathItemNode(getUrl(), new OpenApiPathMethodNode(httpMethod, getOpenApiPathMethodDetailNode())));
    }
}
