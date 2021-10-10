package vRealizeServiceBroker.catalog.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ItemSchema {

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

    public String[] getRequired() {
        return required;
    }

    public ItemSchema setRequired(String[] required) {
        this.required = required;
        return this;
    }
    public ItemSchema setAll(String _type, Boolean _encrypted, JsonNode _properties){
        type = _type;
        encrypted = _encrypted;
        properties = _properties;
        return this;
    }

    private String type;
    private Boolean encrypted;
    private JsonNode properties;

    @Override
    public String toString() {
        return "ItemSchema{" +
                "type='" + type + '\'' +
                ", encrypted=" + encrypted +
                ", properties=" + properties +
                ", required=" + Arrays.toString(required) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSchema that = (ItemSchema) o;
        return Objects.equals(type, that.type) && Objects.equals(encrypted, that.encrypted) && Objects.equals(properties, that.properties) && Arrays.equals(required, that.required);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type, encrypted, properties);
        result = 31 * result + Arrays.hashCode(required);
        return result;
    }

    private String[] required;
}
