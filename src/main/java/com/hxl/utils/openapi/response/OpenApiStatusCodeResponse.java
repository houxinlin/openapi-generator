package com.hxl.utils.openapi.response;

public class OpenApiStatusCodeResponse extends OpenApiResponse{
    public OpenApiStatusCodeResponse(int code,OpenApiResponseDetailNode openApiResponseDetailNode) {
        put(String.valueOf(code),openApiResponseDetailNode);
    }
}
