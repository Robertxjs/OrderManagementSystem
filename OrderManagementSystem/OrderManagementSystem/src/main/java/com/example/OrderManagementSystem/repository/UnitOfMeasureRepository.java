package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnitOfMeasureRepository implements CrudRepository<UnitOfMeasure, Long> {
    private final InMemoryStore<UnitOfMeasure> store;

    public UnitOfMeasureRepository(InMemoryStore<UnitOfMeasure> unitStore) {
        this.store = unitStore;
        // Initialize with default units
        initializeDefaultUnits();
    }

    private void initializeDefaultUnits() {
        if (store.values().isEmpty()) {
            save(new UnitOfMeasure(null, "Piece", "pcs"));
            save(new UnitOfMeasure(null, "Kilogram", "kg"));
            save(new UnitOfMeasure(null, "Meter", "m"));
            save(new UnitOfMeasure(null, "Liter", "L"));
            save(new UnitOfMeasure(null, "Hour", "hr"));
            save(new UnitOfMeasure(null, "Day", "day"));
            save(new UnitOfMeasure(null, "Box", "box"));
            save(new UnitOfMeasure(null, "Package", "pkg"));
        }
    }

    @Override
    public List<UnitOfMeasure> findAll() {
        return store.values();
    }

    @Override
    public Optional<UnitOfMeasure> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public UnitOfMeasure save(UnitOfMeasure entity) {
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

    // Helper method to find by symbol
    public Optional<UnitOfMeasure> findBySymbol(String symbol) {
        return store.values().stream()
                .filter(unit -> unit.getSymbol().equalsIgnoreCase(symbol))
                .findFirst();
    }
}
