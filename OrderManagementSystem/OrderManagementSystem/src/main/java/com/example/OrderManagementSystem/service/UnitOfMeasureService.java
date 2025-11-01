package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureService {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public List<UnitOfMeasure> findAll() {
        return unitOfMeasureRepository.findAll();
    }

    public Optional<UnitOfMeasure> findById(Long id) {
        return unitOfMeasureRepository.findById(id);
    }

    public Optional<UnitOfMeasure> findBySymbol(String symbol) {
        return unitOfMeasureRepository.findBySymbol(symbol);
    }

    public UnitOfMeasure save(UnitOfMeasure unit) {
        return unitOfMeasureRepository.save(unit);
    }

    public UnitOfMeasure createUnit(String name, String symbol) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }

        // Check if unit with same symbol already exists
        if (unitOfMeasureRepository.findBySymbol(symbol).isPresent()) {
            throw new IllegalArgumentException("Unit with symbol " + symbol + " already exists");
        }

        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setName(name.trim());
        unit.setSymbol(symbol.trim());
        return unitOfMeasureRepository.save(unit);
    }

    public boolean deleteById(Long id) {
        return unitOfMeasureRepository.deleteById(id);
    }
}
