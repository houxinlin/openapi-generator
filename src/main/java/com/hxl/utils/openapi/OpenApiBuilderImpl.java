package com.hxl.utils.openapi;

import com.hxl.utils.curl.Curl;
import com.hxl.utils.openapi.parameter.OpenApiHeaderParameterNode;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameterNode;

public class OpenApiBuilderImpl extends BasicOpenApiBuilder {
    private final HttpMethod httpMethod;

    public OpenApiBuilderImpl(String url, HttpMethod httpMethod) {
        super(url);
        this.httpMethod = httpMethod;
    }

    @Override
    public void addToOpenApi(OpenApi api) {
        api.putPath(new OpenApiPathItemNode(getUrl(), new OpenApiPathMethodNode(httpMethod, getOpenApiPathMethodDetailNode())));
    }

    @Override
    public String toCurl() {
        Curl curl = new Curl(httpMethod,getUrl());
        OpenApiPathMethodDetailNode openApiPathMethodDetailNode = getOpenApiPathMethodDetailNode();
        OpenApiUrlParameterNode parameters = ((OpenApiUrlParameterNode) openApiPathMethodDetailNode.get("parameters"));
        for (OpenApiUrlParameter parameter : parameters) {
            if (parameter instanceof OpenApiHeaderParameterNode) {
                OpenApiHeaderParameterNode openApiHeaderParameterNode = (OpenApiHeaderParameterNode) parameter;
                curl.addHeader(openApiHeaderParameterNode.get("name").toString(), "");
            }
        }
        return curl.toString();
    }
}
