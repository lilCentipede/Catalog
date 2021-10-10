package vRealizeServiceBroker.catalog.model;

import java.util.Objects;

public class ItemType {
    public String getId() {
        return id;
    }

    public ItemType setId(String id) {
        this.id = id;
        return this;
    }

    public String getLink() {
        return link;
    }

    public ItemType setLink(String link) {
        this.link = link;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemType setName(String name) {
        this.name = name;
        return this;
    }

    public ItemType setAll(String _id,String _link,String _name){
        id = _id;
        link = _link;
        name = _name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemType itemType = (ItemType) o;
        return Objects.equals(id, itemType.id) && Objects.equals(link, itemType.link) && Objects.equals(name, itemType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, name);
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private String id;
    private String link;
    private String name;

}
