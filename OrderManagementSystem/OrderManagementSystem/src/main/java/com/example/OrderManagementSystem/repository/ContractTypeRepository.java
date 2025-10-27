package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ContractTypeRepository {
    private List<ContractType> contractTypes = new ArrayList<>(Arrays.asList(
            new ContractType("CT001", "Standard Contract", "Customer/Seller"),
            new ContractType("CT002", "Premium Contract", "Customer/Seller"),
            new ContractType("CT003", "Service Agreement", "Service Provider"),
            new ContractType("CT004", "Maintenance Contract", "Service Provider"),
            new ContractType("CT005", "Support Agreement", "Technical Support")
    ));

    public List<ContractType> findAll() {
        return contractTypes;
    }

    public Optional<ContractType> findById(String id) {
        return contractTypes.stream()
                .filter(type -> type.getId().equals(id))
                .findFirst();
    }

    public ContractType save(ContractType contractType) {
        if (contractType.getId() == null) {
            // Generate new ID
            int maxId = contractTypes.stream()
                    .mapToInt(ct -> Integer.parseInt(ct.getId().substring(2)))
                    .max()
                    .orElse(0);
            contractType.setId("CT" + String.format("%03d", maxId + 1));
        }

        // Update existing type or add new one
        for (int i = 0; i < contractTypes.size(); i++) {
            if (contractTypes.get(i).getId().equals(contractType.getId())) {
                contractTypes.set(i, contractType);
                return contractType;
            }
        }

        contractTypes.add(contractType);
        return contractType;
    }

    public boolean deleteById(String id) {
        return contractTypes.removeIf(type -> type.getId().equals(id));
    }


}
