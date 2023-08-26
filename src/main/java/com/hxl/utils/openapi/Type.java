package com.hxl.utils.openapi;

public enum Type {

    file,string,integer,number,array,object,_boolean,any;
    public String getTargetValue(){
        return  this.toString().replace("_","");
    }
}
