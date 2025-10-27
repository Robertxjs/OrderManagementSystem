package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ContractRepository {
    private List<Contract> contracts = new ArrayList<>(Arrays.asList(
            new Contract("CON001", "Annual Support Contract", "CT001", "Active"),
            new Contract("CON002", "Premium Service Agreement", "CT002", "Active"),
            new Contract("CON003", "Maintenance Contract", "CT004", "Active"),
            new Contract("CON004", "Technical Support Agreement", "CT005", "Down"),
            new Contract("CON005", "Standard Service Contract", "CT001", "Active")
    ));

    public List<Contract> findAll() {
        return contracts;
    }

    public Optional<Contract> findById(String id) {
        return contracts.stream()
                .filter(contract -> contract.getId().equals(id))
                .findFirst();
    }

    public Contract save(Contract contract) {
        if (contract.getId() == null) {
            // Generate new ID
            int maxId = contracts.stream()
                    .mapToInt(c -> Integer.parseInt(c.getId().substring(3)))
                    .max()
                    .orElse(0);
            contract.setId("CON" + String.format("%03d", maxId + 1));
        }

        // Update existing contract or add new one
        for (int i = 0; i < contracts.size(); i++) {
            if (contracts.get(i).getId().equals(contract.getId())) {
                contracts.set(i, contract);
                return contract;
            }
        }

        contracts.add(contract);
        return contract;
    }

    public boolean deleteById(String id) {
        return contracts.removeIf(contract -> contract.getId().equals(id));
    }






}
