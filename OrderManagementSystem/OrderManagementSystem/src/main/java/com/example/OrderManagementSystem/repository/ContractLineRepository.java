package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractLine;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.model.Service;
import com.example.OrderManagementSystem.model.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ContractLineRepository {

    @Autowired
    private SellableItemRepo sellableItemRepository;

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private List<ContractLine> contractLines = new ArrayList<>();

    public List<ContractLine> findAll() {
        return contractLines;
    }

    public Optional<ContractLine> findById(String id) {
        return contractLines.stream()
                .filter(line -> line.getId().equals(id)).findFirst();
    }

    public ContractLine save(ContractLine contractLine) {
        if (contractLine.getId() == null) {
            int maxId = contractLines.stream()
                    .mapToInt(cl -> Integer.parseInt(cl.getId().substring(2)))
                    .max()
                    .orElse(0);
            contractLine.setId("CL" + String.format("%03d", maxId + 1));
        }


        for (int i = 0; i < contractLines.size(); i++) {
            if (contractLines.get(i).getId().equals(contractLine.getId())) {
                contractLines.set(i, contractLine);
                return contractLine;
            }
        }

        contractLines.add(contractLine);
        return contractLine;
    }

    public boolean deleteById(String id) {
        return contractLines.removeIf(line -> line.getId().equals(id));
    }

}