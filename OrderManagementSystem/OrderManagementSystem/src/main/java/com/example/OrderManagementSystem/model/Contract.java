package com.example.OrderManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Contract {

    private Long id;
    private String name;
    private Long contractTypeId;
    private String status; // Active/Down
    private List<ContractLine> contractLines;

    public Contract() {
        this.contractLines = new ArrayList<>();
    }

    public Contract(Long id, String name, Long contractTypeId, String status) {
        this();
        this.id = id;
        this.name = name;
        this.contractTypeId = contractTypeId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Long contractTypeId) {
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

    public void activate() {
        this.status = "Active";
    }

    public void deactivate() {
        this.status = "Down";
    }
}
