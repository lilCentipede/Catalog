package vRealizeServiceBroker.catalog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vRealizeServiceBroker.catalog.model.Item;
import vRealizeServiceBroker.catalog.model.ItemFull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class APICatalogRepository  implements CatalogRepository{
    private List<ItemFull> catalog;

    public APICatalogRepository(){
        catalog = new ArrayList<>();
    }
    public void setCatalog(List<ItemFull> catalog) {
        this.catalog = catalog;
    }

    @Override
    public Iterable<ItemFull> findAll(){
        return catalog;
    }

    @Override
    public Optional<ItemFull> findByID(String ID){
        return catalog.stream()
                .filter(x -> x.getId().equals(ID))
                .findFirst();
    }

    @Override
    public ItemFull save(ItemFull item){
        ItemFull item_to_be_added_or_updated = findByID(item.getId()).orElse(item);
        delete(item);
        catalog.add(item_to_be_added_or_updated);
        return item;
    }

    @Override
    public void delete(ItemFull item){
        catalog.remove(item);
    }

    @Override
    public void deleteAll(){
        catalog.clear();
    }
}

