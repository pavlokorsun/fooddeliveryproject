package com.go2it.edu.service;

import com.go2it.edu.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer findById(int id);

    Customer getByName(String name);

    void save(Customer customer);

    List<Customer> getAll();
}