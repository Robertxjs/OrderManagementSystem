package com.example.OrderManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Contract {

    private String id;
    private String name;
    private String contractTypeId;
    private String status; // Active/Down
    private List<ContractLine> contractLines;

    public Contract() {
        this.contractLines = new ArrayList<>();
    }

    public Contract(String id, String name, String contractTypeId, String status) {
        this();
        this.id = id;
        this.name = name;
        this.contractTypeId = contractTypeId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(String contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    public void setContractLines(List<ContractLine> contractLines) {
        this.contractLines = contractLines;
    }

    public void addContractLine(ContractLine contractLine) {
        if (contractLines == null) {
            contractLines = new ArrayList<>();
        }
        contractLines.add(contractLine);
    }

    public boolean isActive() {
        return "Active".equalsIgnoreCase(status);
    }

    public void activate() {
        this.status = "Active";
    }

    public void deactivate() {
        this.status = "Down";
    }
}
