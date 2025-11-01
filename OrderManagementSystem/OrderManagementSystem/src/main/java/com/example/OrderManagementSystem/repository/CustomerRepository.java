package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository implements CrudRepository<Customer, Long> {

    private final InMemoryStore<Customer> store;

    public CustomerRepository(InMemoryStore<Customer> customerStore) {
        this.store = customerStore;
    }
    @Override
    public List<Customer> findAll() {
        return store.values();
    }
    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Customer save(Customer entity) {
        if (entity.getId() == null) {
            entity.setId(store.nextId());
        }
        store.put(entity.getId(), entity);
        return entity;
    }




}
