package vRealizeServiceBroker.catalog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Catalog {
    private List<Item> catalog;

    public Catalog(){
        catalog = new ArrayList<>();
    }

    public List<Item> getCatalog() {
        return catalog;
    }
    public Catalog setCatalog(List<Item> catalog) {
        this.catalog = catalog;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog1 = (Catalog) o;
        return Objects.equals(catalog, catalog1.catalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog);
    }


}
