package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContractTypeRepository implements CrudRepository<ContractType, Long> {
    private final InMemoryStore<ContractType> store;

    public ContractTypeRepository(InMemoryStore<ContractType> contractTypeStore) {
        this.store = contractTypeStore;
        // Initialize with default contract types
        initializeDefaultContractTypes();
    }

    private void initializeDefaultContractTypes() {
        if (store.values().isEmpty()) {
            save(new ContractType(null, "Standard Contract", "Customer/Seller"));
            save(new ContractType(null, "Premium Contract", "Customer/Seller"));
            save(new ContractType(null, "Service Agreement", "Service Provider"));
            save(new ContractType(null, "Maintenance Contract", "Service Provider"));
            save(new ContractType(null, "Support Agreement", "Technical Support"));
        }
    }

    @Override
    public List<ContractType> findAll() {
        return store.values();
    }

    @Override
    public Optional<ContractType> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public ContractType save(ContractType entity) {
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
