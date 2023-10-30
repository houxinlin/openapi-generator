package com.hxl.utils.openapi;

import com.hxl.utils.curl.Curl;
import com.hxl.utils.openapi.body.OpenApiRequestBodyNode;
import com.hxl.utils.openapi.parameter.OpenApiHeaderParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameterNode;
import com.hxl.utils.openapi.parameter.OpenApiUrlQueryParameter;
import com.hxl.utils.openapi.properties.desc.BasicPropertiesDescription;
import com.hxl.utils.openapi.utils.BodyContentUtils;
import com.hxl.utils.openapi.utils.JsonHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        Curl curl = new Curl(httpMethod, getUrl());
        OpenApiPathMethodDetailNode openApiPathMethodDetailNode = getOpenApiPathMethodDetailNode();
        if (openApiPathMethodDetailNode.get("parameters") != null) {
            OpenApiUrlParameterNode parameters = ((OpenApiUrlParameterNode) openApiPathMethodDetailNode.get("parameters"));
            for (OpenApiUrlParameter parameter : parameters) {
                if (parameter instanceof OpenApiHeaderParameter) {
                    OpenApiHeaderParameter openApiHeaderParameter = (OpenApiHeaderParameter) parameter;
                    curl.addHeader(openApiHeaderParameter.get("name").toString(), "");
                }
                if (parameter instanceof OpenApiUrlQueryParameter) {
                    OpenApiUrlQueryParameter openApiUrlQueryParameter = (OpenApiUrlQueryParameter) parameter;
                    curl.addQuery(openApiUrlQueryParameter.get("name").toString(), "");

                }
            }
        }

        if (openApiPathMethodDetailNode.get("requestBody") != null) {
            OpenApiRequestBodyNode requestBody = (OpenApiRequestBodyNode) openApiPathMethodDetailNode.get("requestBody");
            OpenApiNode openApiNode = (OpenApiNode) ((OpenApiNode) requestBody.get("content"))
                    .get(requestBody.getRequestType());
            Object o = openApiNode.get("schema");
            if (o instanceof BodyContentUtils.Schema) {
                BodyContentUtils.Schema schema = (BodyContentUtils.Schema) o;
                OpenApiNode properties=(OpenApiNode) schema.get("properties");
                Map<String,Object> json =new JsonHashMap<>();
                for (String s : properties.keySet()) {
                    json.put(s, ((BasicPropertiesDescription) properties.get(s)).getDefaultValue());
                }
                System.out.println(json);
                curl.setRequestBody(requestBody.getRequestType(),json.toString());


            }

        }
        return curl.toString();
    }
}
