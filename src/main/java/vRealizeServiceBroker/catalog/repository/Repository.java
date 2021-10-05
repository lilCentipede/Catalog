package vRealizeServiceBroker.catalog.repository;

import java.util.Optional;

public interface Repository<T , ID> {
    Iterable<T> findAll();
    Optional<T> findByID(ID id);
    T save(T entity);
    void delete(T entity);
}
