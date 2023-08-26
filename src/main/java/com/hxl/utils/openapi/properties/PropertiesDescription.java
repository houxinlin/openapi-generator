package com.hxl.utils.openapi.properties;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.Type;


public abstract class PropertiesDescription extends OpenApiNode {
    public PropertiesDescription(Type type,String description) {
        set("type",type.getTargetValue());
        set("description",description);
    }
    //    public PropertiesDescription(Properties properties) {
//        if (properties instanceof BasicProperties){
//            set("type",((BasicProperties) properties).getType());
//            set("description",((BasicProperties) properties).getDescription());
//        }
//    }

    //    private BasicProperties create(String name, Type type) {
//        return new BasicProperties(name, type, description);
//    }
//    public BasicProperties newString(String name) {
//        return new BasicProperties(name, Type.string, description);
//    }
//    public BasicProperties newNumber(String name) {
//        return create(name, Type.number);
//    }
//
//    public BasicProperties newFile(String name) {
//        return create(name, Type.file);
//    }
//
//    public ArrayProperties newArray(Properties arrays) {
//        return  new ArrayProperties(arrays);
//    }
//    public ObjectProperties newObject(Properties properties) {
//        return new ObjectProperties(properties);
//    }
//
//    public BasicProperties newBoolean(String name) {
//        return create(name, Type._boolean);
//    }
//    public PropertiesDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
