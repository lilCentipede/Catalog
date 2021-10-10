package vRealizeServiceBroker.catalog.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItemSchema {
    private String type;
    private Boolean encrypted;
    private JsonNode properties;
    private List<String> required;

    @Override
    public String toString() {
        return "ItemSchema{" +
                "type='" + type + '\'' +
                ", encrypted=" + encrypted +
                ", properties=" + properties +
                ", required=" + required +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSchema that = (ItemSchema) o;
        return Objects.equals(type, that.type) && Objects.equals(encrypted, that.encrypted) && Objects.equals(properties, that.properties) && Objects.equals(required, that.required);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, encrypted, properties, required);
    }



    public List<String> getRequired() {
        return required;
    }

    public ItemSchema setRequired(JsonNode _required) {
        this.required = new ArrayList<>();
        if(_required.isArray()){
            for(var el : _required){
                this.required.add(el.asText());
            }
        }
        return this;
    }


    public String getType() {
        return type;
    }

    public ItemSchema setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getEncrypted() {
        return encrypted;
    }

    public ItemSchema setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
        return this;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public ItemSchema setProperties(JsonNode properties) {
        this.properties = properties;
        return this;
    }

    public ItemSchema setAll(String _type, Boolean _encrypted, JsonNode _properties,JsonNode _required){
        type = _type;
        encrypted = _encrypted;
        properties = _properties;
        this.setRequired(_required);
        return this;
    }

}
