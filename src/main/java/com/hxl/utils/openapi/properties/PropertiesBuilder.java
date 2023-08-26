package com.hxl.utils.openapi.properties;

import com.hxl.utils.openapi.Type;
import com.hxl.utils.openapi.properties.desc.BasicPropertiesDescription;
import com.hxl.utils.openapi.properties.desc.ObjectPropertiesDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PropertiesBuilder {
    private final List<Properties> propertiesList = new ArrayList<>();

    private PropertiesDescription getPropertiesDescription(String description, Type type) {
        return new BasicPropertiesDescription(type, description);
    }

    public PropertiesBuilder addProperties(String name, String description, Type type) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, type)));
        return this;
    }

    public PropertiesBuilder addStringProperties(String name, String description) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, Type.string)));
        return this;
    }

    public PropertiesBuilder addNumberProperties(String name, String description) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, Type.number)));
        return this;
    }

    public PropertiesBuilder addBooleanProperties(String name, String description) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, Type._boolean)));
        return this;
    }

    public PropertiesBuilder addFileProperties(String name, String description) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, Type.file)));
        return this;
    }

    public PropertiesBuilder addArrayProperties(String name, String description) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, Type.array)));
        return this;
    }

    public PropertiesBuilder addIntegerProperties(String name, String description) {
        propertiesList.add(new Properties(name, getPropertiesDescription(description, Type.integer)));
        return this;
    }

    public PropertiesBuilder addObjectProperties(String name, Consumer<PropertiesBuilder> consumer, String description) {
        PropertiesBuilder propertiesBuilder = new PropertiesBuilder();
        consumer.accept(propertiesBuilder);
        List<Properties> properties = new ArrayList<>(propertiesBuilder.getPropertiesList());
        propertiesList.add(new Properties(name, new ObjectPropertiesDescription(properties, description)));
        return this;
    }

    public ObjectProperties object() {
        return new ObjectProperties(this.propertiesList);
    }

    public List<Properties> getPropertiesList() {
        return propertiesList;
    }

    public ArrayProperties array(BasicProperties basicProperties) {
        return new ArrayProperties(basicProperties);
    }
}
