package com.hxl.utils.openapi.utils;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.properties.*;

public class BodyContentUtils {
//    public static OpenApiNode getContent(String type, ArrayProperties properties) {
//        OpenApiNode openApi = new OpenApiNode();
//        Schema schema = new Schema("array");
//        schema.setItem(properties);
//        openApi.set(type, schema);
//        return openApi;
//    }

    public static OpenApiNode getContent(String type,BasicProperties properties) {
        OpenApiNode openApi = new OpenApiNode();
        Schema schema = new Schema(properties instanceof ArrayProperties?"array":"object",properties);
        openApi.set(type, new OpenApiNode().set("schema",schema));
        return openApi;
    }

    public static class Schema extends OpenApiNode {
        private final OpenApiNode propertiesOpenApiNode =new OpenApiNode();
        public Schema(String type,BasicProperties properties) {
            set("type",type);
            applyToParent(this,properties);
//            OpenApiNode openApiNode = new OpenApiNode()
//                    .set("type", type);
//            if ( properties instanceof ArrayProperties){
//                openApiNode.set("items",new OpenApiNode().set("type"))
//            }
////                    .set("properties", propertiesOpenApiNode);
//
//            set("schema", openApiNode);
//
//            for (KeyValue property : properties.getProperties()) {
//                propertiesOpenApiNode.set(property.getKey(),property.getValue());
//            }
        }

        public void applyToParent(OpenApiNode openApiNode,BasicProperties properties){
            if (properties instanceof ArrayProperties) {
                BasicProperties basicProperties = ((ArrayProperties) properties).getBasicProperties();
                OpenApiNode nextNode = new OpenApiNode();
                openApiNode.set("type","array");
                openApiNode.set("items",nextNode);
                applyToParent(nextNode,basicProperties);
            }
            if (properties instanceof ObjectProperties){
                ObjectProperties objectProperties = (ObjectProperties) properties;
                OpenApiNode objectPropertiesMap = new OpenApiNode();
                for (Properties property : objectProperties.getProperties()) {
                    objectPropertiesMap.set(property.getKey(),property.getValue());
                }
                openApiNode.set("properties",objectPropertiesMap);

            }
        }
        public Schema setItem(BasicProperties propertiesItem) {

            return this;
        }
    }
}
