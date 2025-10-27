package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer("C001", "Acme Corporation", "USD"),
            new Customer("C002", "Tech Solutions Ltd", "EUR"),
            new Customer("C003", "Global Industries", "USD"),
            new Customer("C004", "Startup Ventures", "GBP"),
            new Customer("C005", "Enterprise Systems", "USD")
    ));

    public List<Customer> findAll() {
        return customers;
    }

    public Optional<Customer> findById(String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            // Generate new ID
            int maxId = customers.stream()
                    .mapToInt(c -> Integer.parseInt(c.getId().substring(1)))
                    .max()
                    .orElse(0);
            customer.setId("C" + String.format("%03d", maxId + 1));
        }

        // Update existing customer or add new one
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customer.getId())) {
                customers.set(i, customer);
                return customer;
            }
        }

        customers.add(customer);
        return customer;
    }

    public boolean deleteById(String id) {
        return customers.removeIf(customer -> customer.getId().equals(id));
    }


}
