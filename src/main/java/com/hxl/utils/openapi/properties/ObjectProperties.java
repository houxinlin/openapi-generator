package com.hxl.utils.openapi.properties;

import java.util.List;

public class ObjectProperties extends BasicProperties {
    private List<Properties> properties;

    public ObjectProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }
}
