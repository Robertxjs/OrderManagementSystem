package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.SellableItem;

import com.example.OrderManagementSystem.repository.InMemoryStore;

import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository

public class SellableItemRepository implements CrudRepository<SellableItem, Long> {

    private final InMemoryStore<SellableItem> store;

    public SellableItemRepository(InMemoryStore<SellableItem> sellableItemStore) {

        this.store = sellableItemStore;

    }

    @Override

    public List<SellableItem> findAll() {

        return store.values();

    }

    @Override

    public Optional<SellableItem> findById(Long id) {

        return Optional.ofNullable(store.get(id));

    }

    @Override

    public SellableItem save(SellableItem entity) {

        if (entity.getId() == null) {

            entity.setId(store.nextId());

        }

        store.put(entity.getId(), entity);

        return entity;

    }

    @Override

    public boolean deleteById(Long id) {

        return store.remove(id) != null;

    }

}

