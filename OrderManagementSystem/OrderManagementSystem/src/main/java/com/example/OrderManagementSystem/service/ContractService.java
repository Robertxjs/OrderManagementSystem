package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.*;
import com.example.OrderManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractLineRepository contractLineRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private SellableItemRepo sellableItemRepository;

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Optional<Contract> findById(String id) {
        return contractRepository.findById(id);
    }

    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    public boolean deleteById(String id) {
        return contractRepository.deleteById(id);
    }



    public Contract createContract(String name, String contractTypeId, String status) {
        ContractType contractType = contractTypeRepository.findById(contractTypeId)
                .orElseThrow(() -> new RuntimeException("Contract type not found"));

        Contract contract = new Contract();
        contract.setName(name);
        contract.setContractTypeId(contractTypeId);
        contract.setStatus(status);

        return contractRepository.save(contract);
    }

    public ContractLine addContractLine(String contractId, String itemId, String unitId, double quantity) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        SellableItem item = sellableItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        UnitOfMeasure unit = unitOfMeasureRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        ContractLine contractLine = new ContractLine();
        contractLine.setItem(item);
        contractLine.setUnit(unit);
        contractLine.setQuantity(quantity);

        ContractLine savedLine = contractLineRepository.save(contractLine);
        contract.addContractLine(savedLine);
        contractRepository.save(contract);

        return savedLine;
    }


    public Contract activateContract(String contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        contract.activate();
        return contractRepository.save(contract);
    }

    public Contract deactivateContract(String contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        contract.deactivate();
        return contractRepository.save(contract);
    }


}
