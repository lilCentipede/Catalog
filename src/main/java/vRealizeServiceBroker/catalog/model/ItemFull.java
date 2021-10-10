package vRealizeServiceBroker.catalog.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemFull {
    private String id;
    private String name;
    private String description;
    private ItemType type;
    private List<String> projectIds;
    private String createdAt;
    private String createdBy;
    private String lastUpdatedAt;
    private String getLastUpdatedBy;
    private ItemSchema schema;
    private String iconID;
    private Integer bulkedRequestLimit;


    public String getId() {
        return id;
    }

    public ItemFull setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemFull setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemType getType() {
        return type;
    }

    public ItemFull setType(ItemType type) {
        this.type = type;
        return this;
    }

    public List<String> getProjectIds() {
        return projectIds;
    }

    public ItemFull setProjectIds(List<String> projectIds) {
        this.projectIds = new ArrayList<>();
        this.projectIds = projectIds;
        return this;
    }
    public ItemFull setProjectIds(final JsonNode prIds){
        this.projectIds = new ArrayList<>();
        if(prIds.isArray()){
            for(var el : prIds){
                this.projectIds.add(el.asText());
            }
        }
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ItemFull setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ItemFull setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public ItemFull setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public String getGetLastUpdatedBy() {
        return getLastUpdatedBy;
    }

    public ItemFull setGetLastUpdatedBy(String getLastUpdatedBy) {
        this.getLastUpdatedBy = getLastUpdatedBy;
        return this;
    }

    public ItemSchema getSchema() {
        return schema;
    }

    public ItemFull setSchema(ItemSchema schema) {
        this.schema = schema;
        return this;
    }

    public String getIconID() {
        return iconID;
    }

    public ItemFull setIconID(String iconID) {
        this.iconID = iconID;
        return this;
    }

    public Number getBulkedRequestLimit() {
        return bulkedRequestLimit;
    }

    public ItemFull setBulkedRequestLimit(Integer bulkedRequestLimit) {
        this.bulkedRequestLimit = bulkedRequestLimit;
        return this;
    }
}
