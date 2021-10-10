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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemFull itemFull = (ItemFull) o;
        return Objects.equals(id, itemFull.id) && Objects.equals(name, itemFull.name)
                && Objects.equals(description, itemFull.description) && Objects.equals(type, itemFull.type)
                && Objects.equals(projectIds, itemFull.projectIds) && Objects.equals(createdAt, itemFull.createdAt)
                && Objects.equals(createdBy, itemFull.createdBy)
                && Objects.equals(lastUpdatedAt, itemFull.lastUpdatedAt)
                && Objects.equals(getLastUpdatedBy, itemFull.getLastUpdatedBy)
                && Objects.equals(schema, itemFull.schema)
                && Objects.equals(iconID, itemFull.iconID) && Objects.equals(bulkedRequestLimit, itemFull.bulkedRequestLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, projectIds, createdAt, createdBy, lastUpdatedAt, getLastUpdatedBy,  iconID, bulkedRequestLimit);
    }

    public String getId() {
        return id;
    }

    public ItemFull setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String
    toString() {
        return "ItemFull{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", projectIds=" + projectIds +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", getLastUpdatedBy='" + getLastUpdatedBy + '\'' +
                ", schema=" + schema +
                ", iconID='" + iconID + '\'' +
                ", bulkedRequestLimit=" + bulkedRequestLimit +
                '}';
    }

    public String getName() {
        return name;
    }

    public ItemFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getDecription() {
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
