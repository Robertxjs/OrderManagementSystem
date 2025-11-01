package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.repository.CustomerRepository;
import com.example.OrderManagementSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean deleteById(String id) {
        return customerRepository.deleteById(id);
    }



    public Customer createCustomer(String name, String currency) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setCurrency(currency);
        return customerRepository.save(customer);
    }




}
