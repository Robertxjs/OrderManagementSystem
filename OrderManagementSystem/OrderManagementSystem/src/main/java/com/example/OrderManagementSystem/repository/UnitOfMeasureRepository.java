package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UnitOfMeasureRepository {
    private List<UnitOfMeasure> units = new ArrayList<>(Arrays.asList(
            new UnitOfMeasure("U001", "Piece", "pcs"),
            new UnitOfMeasure("U002", "Kilogram", "kg"),
            new UnitOfMeasure("U003", "Meter", "m"),
            new UnitOfMeasure("U004", "Liter", "L"),
            new UnitOfMeasure("U005", "Hour", "hr"),
            new UnitOfMeasure("U006", "Day", "day"),
            new UnitOfMeasure("U007", "Box", "box"),
            new UnitOfMeasure("U008", "Package", "pkg")
    ));

    public List<UnitOfMeasure> findAll() {
        return units;
    }

    public Optional<UnitOfMeasure> findById(String id) {
        return units.stream()
                .filter(unit -> unit.getId().equals(id))
                .findFirst();
    }

    public UnitOfMeasure save(UnitOfMeasure unit) {
        if (unit.getId() == null) {
            // Generate new ID
            int maxId = units.stream()
                    .mapToInt(u -> Integer.parseInt(u.getId().substring(1)))
                    .max()
                    .orElse(0);
            unit.setId("U" + String.format("%03d", maxId + 1));
        }

        // Update existing unit or add new one
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getId().equals(unit.getId())) {
                units.set(i, unit);
                return unit;
            }
        }

        units.add(unit);
        return unit;
    }

    public boolean deleteById(String id) {
        return units.removeIf(unit -> unit.getId().equals(id));
    }

    public Optional<UnitOfMeasure> findBySymbol(String symbol) {
        return units.stream()
                .filter(unit -> unit.getSymbol().equalsIgnoreCase(symbol))
                .findFirst();
    }
}
