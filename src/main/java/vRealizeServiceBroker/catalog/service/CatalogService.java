package vRealizeServiceBroker.catalog.service;

import vRealizeServiceBroker.catalog.model.ItemFull;

import java.util.Optional;

public interface CatalogService {
    Iterable<ItemFull> findAll();

    Optional<ItemFull> findByID(String id);

    ItemFull save(ItemFull item);

    void delete(ItemFull item);
}
