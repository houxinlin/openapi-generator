package com.hxl.utils.openapi.parameter;

import com.hxl.utils.openapi.OpenApiNode;
import com.hxl.utils.openapi.Type;

public abstract class OpenApiUrlParameter extends OpenApiNode {
    public OpenApiUrlParameter(String name, String description, boolean required, Type type) {
        set("name",name);
        set("description",description);
        set("required",required);
        set("schema",new OpenApiNode().set("type",type.getTargetValue()));
        set("in",getIn());
    }
    public abstract String getIn();
}
