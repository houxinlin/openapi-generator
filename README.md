# openapi-generator
用于根据参数生成openapi json文档


# 例子
```java
 OpenApiStatusCodeResponse openApiStatusCodeResponse = new OpenApiStatusCodeResponse(200, new OpenApiResponseDetailNode("响应成功"));
        ObjectProperties object = new PropertiesBuilder()
        .addObjectProperties("a", (v) -> {
             v.addProperties("a", "描述", Type.string);
        }, "描述")
        .addStringProperties("sss", "描述")
        .addProperties("test", "测试", Type.string)
        .addObjectProperties("address", (v) -> {
            v.addProperties("city", "城市", Type.string);
            v.addProperties("area", "区域", Type.string);
        }, "地址").object();
        OpenApiRequestBodyNode openApiRequestBodyNode = new OpenApiApplicationJSONBodyNode(object);
        OpenApi api = OpenApiBuilder.get("/user/get/{userId}", "获取用户")
            .addParameter(new OpenApiUrlPathParameter("userId", "sdd", true, Type.number))
            .addParameter(new OpenApiHeaderParameterNode("name", "asd", false, Type.string))
            .addParameter(new OpenApiUrlQueryParameter("urlpaa", "asd", false, Type._boolean))
            .setRequestBody(openApiRequestBodyNode)
            .setResponse(openApiStatusCodeResponse).build();
        System.out.println(api.toJSON());
```

输出如下

```json
{
  "openapi": "3.0.0",
  "paths": {
    "/user/get/{userId}": {
      "get": {
        "summary": "获取用户",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "object",
                "properties": {
                  "a": {
                    "description": "描述",
                    "type": "object",
                    "properties": {
                      "a": {
                        "description": "描述",
                        "type": "string"
                      }
                    }
                  },
                  "sss": {
                    "description": "描述",
                    "type": "string"
                  },
                  "address": {
                    "description": "地址",
                    "type": "object",
                    "properties": {
                      "area": {
                        "description": "区域",
                        "type": "string"
                      },
                      "city": {
                        "description": "城市",
                        "type": "string"
                      }
                    }
                  },
                  "test": {
                    "description": "测试",
                    "type": "string"
                  }
                }
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "响应成功"
          }
        },
        "parameters": [
          {
            "schema": {
              "type": "number"
            },
            "in": "path",
            "name": "userId",
            "description": "sdd",
            "required": true
          },
          {
            "schema": {
              "type": "string"
            },
            "in": "header",
            "name": "name",
            "description": "asd",
            "required": false
          },
          {
            "schema": {
              "type": "boolean"
            },
            "in": "query",
            "name": "urlpaa",
            "description": "asd",
            "required": false
          }
        ]
      }
    }
  }
}
```