package vRealizeServiceBroker.catalog.service;

import vRealizeServiceBroker.catalog.model.Item;

import java.util.Optional;

public interface CatalogService {
    Iterable<Item> findAll();

    Optional<Item> findByID(String id);

    Item save(Item item);

    void delete(Item item);
}
