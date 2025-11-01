package com.example.OrderManagementSystem.controller;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ContractLine;
import com.example.OrderManagementSystem.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/contracts")
@CrossOrigin(origins = "${app.cors.allowed-origins}", maxAge = 3600)
public class ContractController {
    @Autowired
    private ContractService contractService;
    // Get all contracts
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.findAll();
        return ResponseEntity.ok(contracts);
    }
    // Get contract by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Optional<Contract> contract = contractService.findById(id);
        return contract.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Create new contract
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody CreateContractRequest request) {
        Contract contract = contractService.createContract(
                request.getName(),
                request.getContractTypeId(),
                request.getStatus()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(contract);
    }
    // Update contract
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(
            @PathVariable Long id,
            @RequestBody Contract contract) {
        Optional<Contract> existingContract = contractService.findById(id);
        if (existingContract.isPresent()) {
            contract.setId(id);
            Contract updatedContract = contractService.save(contract);
            return ResponseEntity.ok(updatedContract);
        }
        return ResponseEntity.notFound().build();
    }
    // Delete contract
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        boolean deleted = contractService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    // Activate contract
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Contract> activateContract(@PathVariable Long id) {
        Contract contract = contractService.activateContract(id);
        return ResponseEntity.ok(contract);
    }
    // Deactivate contract
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Contract> deactivateContract(@PathVariable Long id) {
        Contract contract = contractService.deactivateContract(id);
        return ResponseEntity.ok(contract);
    }
    // Add contract line
    @PostMapping("/{contractId}/lines")
    public ResponseEntity<ContractLine> addContractLine(
            @PathVariable Long contractId,
            @RequestBody AddContractLineRequest request) {
        ContractLine contractLine = contractService.addContractLine(
                contractId,
                request.getItemId(),
                request.getUnitId(),
                request.getQuantity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(contractLine);
    }
    // Request DTOs (no validation annotations)
    public static class CreateContractRequest {
        private String name;
        private Long contractTypeId;
        private String status;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Long getContractTypeId() { return contractTypeId; }
        public void setContractTypeId(Long contractTypeId) { this.contractTypeId = contractTypeId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
    public static class AddContractLineRequest {
        private Long itemId;
        private Long unitId;
        private double quantity;
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }
        public Long getUnitId() { return unitId; }
        public void setUnitId(Long unitId) { this.unitId = unitId; }
        public double getQuantity() { return quantity; }
        public void setQuantity(double quantity) { this.quantity = quantity; }
    }
}