package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.*;
import com.example.OrderManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderManagementController {

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    // Contract Types
    @GetMapping("/contract-types")
    public ResponseEntity<List<ContractType>> getAllContractTypes() {
        return ResponseEntity.ok(contractTypeRepository.findAll());
    }

    @GetMapping("/contract-types/{id}")
    public ResponseEntity<ContractType> getContractTypeById(@PathVariable String id) {
        return contractTypeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Unit of Measures
    @GetMapping("/units")
    public ResponseEntity<List<UnitOfMeasure>> getAllUnits() {
        return ResponseEntity.ok(unitOfMeasureRepository.findAll());
    }

    @GetMapping("/units/{id}")
    public ResponseEntity<UnitOfMeasure> getUnitById(@PathVariable String id) {
        return unitOfMeasureRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // System status
    @GetMapping("/status")
    public ResponseEntity<SystemStatus> getSystemStatus() {
        SystemStatus status = new SystemStatus();
        status.setTotalCustomers(contractTypeRepository.findAll().size()); // Placeholder
        status.setTotalOrders(unitOfMeasureRepository.findAll().size()); // Placeholder
        status.setTotalContracts(contractTypeRepository.findAll().size());
        status.setTotalItems(unitOfMeasureRepository.findAll().size()); // Placeholder
        status.setSystemStatus("Running");
        return ResponseEntity.ok(status);
    }

    // Initialize all sample data
    @PostMapping("/initialize-all")
    public ResponseEntity<String> initializeAllSampleData() {
        // This would initialize all repositories with sample data
        return ResponseEntity.ok("All sample data initialized successfully");
    }

    public static class SystemStatus {
        private int totalCustomers;
        private int totalOrders;
        private int totalContracts;
        private int totalItems;
        private String systemStatus;

        public int getTotalCustomers() { return totalCustomers; }
        public void setTotalCustomers(int totalCustomers) { this.totalCustomers = totalCustomers; }
        public int getTotalOrders() { return totalOrders; }
        public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }
        public int getTotalContracts() { return totalContracts; }
        public void setTotalContracts(int totalContracts) { this.totalContracts = totalContracts; }
        public int getTotalItems() { return totalItems; }
        public void setTotalItems(int totalItems) { this.totalItems = totalItems; }
        public String getSystemStatus() { return systemStatus; }
        public void setSystemStatus(String systemStatus) { this.systemStatus = systemStatus; }
    }
}