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
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class OpenApiBuilderImpl extends BasicOpenApiBuilder {
    private final HttpMethod httpMethod;
    private final BiFunction<BasicPropertiesDescription, OpenApiNode, Object> DEFAULT_OBJECT_FACTORY = new BiFunction<BasicPropertiesDescription, OpenApiNode, Object>() {
        @Override
        public Object apply(BasicPropertiesDescription basicPropertiesDescription, OpenApiNode openApiNode) {
            Map<String, Object> result = new JsonHashMap<>();
            for (String key : openApiNode.keySet()) {
                if (openApiNode.get(key) instanceof BasicPropertiesDescription) {
                    result.put(key, basicPropertiesDescription.doGetDefaultValue(((BasicPropertiesDescription) openApiNode.get(key)), this));
                }
            }
            return result;
        }
    };
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
        return toCurl(s -> null, s -> null, () -> null);
    }
    @Override
    public String toCurl(Function<String, Object> headerValueFactory, Function<String, Object> queryValueFactory, Supplier<String> requestBodyCacheGet) {
        Curl curl = new Curl(httpMethod, getUrl());
        OpenApiPathMethodDetailNode openApiPathMethodDetailNode = getOpenApiPathMethodDetailNode();
        if (openApiPathMethodDetailNode.get("parameters") != null) {
            OpenApiUrlParameterNode parameters = ((OpenApiUrlParameterNode) openApiPathMethodDetailNode.get("parameters"));
            for (OpenApiUrlParameter parameter : parameters) {
                if (parameter instanceof OpenApiHeaderParameter) {
                    OpenApiHeaderParameter openApiHeaderParameter = (OpenApiHeaderParameter) parameter;
                    String name = openApiHeaderParameter.get("name").toString();
                    curl.addHeader(name, Optional.ofNullable(headerValueFactory.apply(name).toString()).orElse(""));
                }
                if (parameter instanceof OpenApiUrlQueryParameter) {
                    OpenApiUrlQueryParameter openApiUrlQueryParameter = (OpenApiUrlQueryParameter) parameter;
                    String name = openApiUrlQueryParameter.get("name").toString();
                    curl.addQuery(name, Optional.ofNullable(queryValueFactory.apply(name).toString()).orElse(""));

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
                OpenApiNode properties = (OpenApiNode) schema.get("properties");
                String cacheRequestBody = requestBodyCacheGet.get();
                if ("application/json".equalsIgnoreCase(requestBody.getRequestType())) {
                    curl.setRequestBody(requestBody.getRequestType(), cacheRequestBody == null ? getBodyFormJsonApplication(properties) : cacheRequestBody);
                }
                if ("application/x-www-form-urlencoded".equalsIgnoreCase(requestBody.getRequestType()) ||
                        "multipart/form-data".equalsIgnoreCase(requestBody.getRequestType())) {
                    curl.setRequestBody(requestBody.getRequestType(), cacheRequestBody == null ? getBodyFormUrlencoded(properties):cacheRequestBody);
                }
                curl.addHeaderIfMiss("content-type",requestBody.getRequestType());
            }
        }
        return curl.toString();
    }

    private String getBodyFormJsonApplication(OpenApiNode properties) {
        Map<String, Object> json = new JsonHashMap<>();
        for (String s : properties.keySet()) {
            json.put(s, ((BasicPropertiesDescription) properties.get(s)).getDefaultValue(DEFAULT_OBJECT_FACTORY));
        }
        return json.toString();
    }

    private String getBodyFormUrlencoded(OpenApiNode properties) {
        final StringBuilder result = new StringBuilder();
        for (String s : properties.keySet()) {
            result.append(s).append("=").append(((BasicPropertiesDescription) properties.get(s)).getDefaultValue(DEFAULT_OBJECT_FACTORY)).append("&");
        }
        if (result.toString().endsWith("&")) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
