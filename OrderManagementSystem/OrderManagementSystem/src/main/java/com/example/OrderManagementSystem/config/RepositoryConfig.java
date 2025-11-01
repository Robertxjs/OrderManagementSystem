package com.example.OrderManagementSystem.config;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.repository.InMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public InMemoryStore<SellableItem> sellableItemStore() {
        return new InMemoryStore<>();
    }

    @Bean
    public InMemoryStore<Contract> contractStore() {
        return new InMemoryStore<>();
    }

    @Bean
    public InMemoryStore<ContractType> contractTypeStore() {
        return new InMemoryStore<>();
    }

    @Bean
    public InMemoryStore<UnitOfMeasure> unitOfMeasureStore() {
        return new InMemoryStore<>();
    }
}
