package com.hxl.utils.openapi;

import com.hxl.utils.openapi.body.OpenApiRequestBodyNode;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlParameterNode;
import com.hxl.utils.openapi.response.OpenApiStatusCodeResponse;

public class OpenApiPathMethodDetailNode extends OpenApiNode {
    public OpenApiPathMethodDetailNode() {
        set("parameters", new OpenApiUrlParameterNode());
    }

    public OpenApiPathMethodDetailNode setSummary(String value) {
        set("summary", value);
        return this;
    }

    public OpenApiPathMethodDetailNode setResponse(OpenApiStatusCodeResponse openApiStatusCodeResponse) {
        set("responses", openApiStatusCodeResponse);
        return this;
    }

    public OpenApiPathMethodDetailNode setRequestBody(OpenApiRequestBodyNode openApiRequestBodyNode) {
        set("requestBody", openApiRequestBodyNode);
        return this;
    }
    public OpenApiPathMethodDetailNode addParameter(OpenApiUrlParameter parameter) {
        ((OpenApiUrlParameterNode) get("parameters")).add(parameter);
        return this;
    }
}
