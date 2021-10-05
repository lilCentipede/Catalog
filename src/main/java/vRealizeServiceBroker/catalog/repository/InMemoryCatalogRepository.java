package vRealizeServiceBroker.catalog.repository;

import org.springframework.stereotype.Repository;
import vRealizeServiceBroker.catalog.model.Item;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCatalogRepository implements CatalogRepository {
    private List<Item> catalog = FakeData.CATALOG;

    @Override
    public Iterable<Item> findAll(){
        return catalog;
    }

    @Override
    public Optional<Item> findByID(String ID){
        return catalog.stream()
                .filter(x -> x.getId().equals(ID))
                .findFirst();
    }

    @Override
    public Item save(Item item){
        Item item_to_be_added_or_updated = findByID(item.getId()).orElse(item);
        delete(item);
        catalog.add(item_to_be_added_or_updated);
        return item;
    }

    @Override
    public void delete(Item item){
        catalog.remove(item);
    }
}
