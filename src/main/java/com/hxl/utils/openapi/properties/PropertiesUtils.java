package com.hxl.utils.openapi.properties;

import com.hxl.utils.openapi.Type;
import com.hxl.utils.openapi.properties.desc.BasicPropertiesDescription;
import com.hxl.utils.openapi.properties.desc.ObjectPropertiesDescription;

import java.util.List;

public class PropertiesUtils {
    public static Properties createString(String name, String description){
        return  new Properties(name,new BasicPropertiesDescription(Type.string,description));
    }
    public static Properties createInteger(String name, String description){
        return  new Properties(name,new BasicPropertiesDescription(Type.integer,description));
    }
    public static Properties createFile(String name, String description){
        return  new Properties(name,new BasicPropertiesDescription(Type.file,description));
    }
    public static Properties createKeyValueObject(String key, List<Properties> properties, String description) {
        return new Properties(key,new ObjectPropertiesDescription(properties,description));
    }
}
