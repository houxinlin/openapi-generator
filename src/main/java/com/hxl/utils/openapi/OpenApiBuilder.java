package com.hxl.utils.openapi;

import com.hxl.utils.openapi.body.OpenApiRequestBodyNode;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameter;
import com.hxl.utils.openapi.response.OpenApiStatusCodeResponse;

public abstract class OpenApiBuilder {
    private final OpenApiPathMethodDetailNode openApiPathMethodDetailNode = new OpenApiPathMethodDetailNode();
    public OpenApiPathMethodDetailNode getOpenApiPathMethodDetailNode() {
        return openApiPathMethodDetailNode;
    }
    public static  OpenApiBuilder create(String url, String summary,HttpMethod method){
        OpenApiBuilderImpl openApiBuilder = new OpenApiBuilderImpl(url, method);
        openApiBuilder.getOpenApiPathMethodDetailNode().setSummary(summary);
        return openApiBuilder;
    }
    public static OpenApiBuilder get(String url, String summary) {
        return create(url,summary, HttpMethod.get);
    }
    public static OpenApiBuilder post(String url, String summary) {
        return create(url,summary, HttpMethod.post);
    }
    public static OpenApiBuilder put(String url, String summary) {
        return create(url,summary,HttpMethod.put);
    }
    public static OpenApiBuilder delete(String url, String summary) {
        return create(url,summary, HttpMethod.delete);
    }
    public static OpenApiBuilder head(String url, String summary) {
        return create(url,summary, HttpMethod.head);
    }
    public static OpenApiBuilder patch(String url, String summary) {
        return create(url,summary, HttpMethod.patch);
    }
    public OpenApiBuilder addParameter(OpenApiUrlParameter openApiUrlParameter) {
        openApiPathMethodDetailNode.addParameter(openApiUrlParameter);
        return this;
    }

    public OpenApiBuilder setRequestBody(OpenApiRequestBodyNode openApiRequestBodyNode) {
        openApiPathMethodDetailNode.setRequestBody(openApiRequestBodyNode);
        return this;
    }

    public OpenApiBuilder setResponse(OpenApiStatusCodeResponse openApiStatusCodeResponse) {
        openApiPathMethodDetailNode.setResponse(openApiStatusCodeResponse);
        return this;
    }
    public abstract void  addToOpenApi(OpenApi api) ;
}
