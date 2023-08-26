package com.hxl.utils.openapi;

import com.google.gson.Gson;

/**
 * {
 *   "openapi": "3.0.0",
 *   "info": {
 *     "title": "示例API",
 *     "version": "1.0.0",
 *     "description": "这是一个演示用的示例API。",
 *     "contact": {
 *       "name": "API团队",
 *       "email": "api@example.com"
 *     }
 *   },
 *   "paths": {
 *     "/users": {
 *       "get": {
 *         "summary": "获取用户列表",
 *         "responses": {
 *           "200": {
 *             "description": "成功响应",
 *             "content": {
 *               "application/json": {
 *                 "example": [
 *                   {
 *                     "id": 1,
 *                     "username": "user1"
 *                   },
 *                   {
 *                     "id": 2,
 *                     "username": "user2"
 *                   }
 *                 ]
 *               }
 *             }
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 */
public class OpenApi  extends OpenApiNode{
    public OpenApi() {
        set("openapi","3.0.0");
        set("paths",new OpenApiNode());
    }

    public OpenApi putPath(OpenApiPathItemNode apiNode){
        ((OpenApiNode) get("paths")).put(apiNode.getUrlPath(),apiNode.getMethodNode());
//        set(apiNode.getUrlPath(),apiNode);
        return this;
    }
    public String toJSON(){
        return  new Gson().toJson(this);
    }
}
