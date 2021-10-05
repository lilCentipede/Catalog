package vRealizeServiceBroker.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vRealizeServiceBroker.catalog.model.Item;
import vRealizeServiceBroker.catalog.repository.CatalogRepository;

import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService{

    private CatalogRepository repository;

    @Override
    public Iterable<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Item> findByID(String id) {
        return repository.findByID(id);
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void delete(Item item) {
        repository.delete(item);
    }


    @Autowired
    public void setRepository(CatalogRepository repository) {
        this.repository = repository;
    }


}
