package com.hxl.utils.openapi;

public enum Type {
    file,string,integer,number,array,object,_boolean,any;
    public String getTargetValue(){
        return  this.toString().replace("_","");
    }

    public static Type parse(String name,Type def){
        try {
            return Type.valueOf(name.toLowerCase());
        }catch (Exception e){}
        return def;
    }
}
