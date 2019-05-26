package com.go2it.edu.repository;

import com.go2it.edu.entity.Customer;
import java.util.List;

public interface ICustomerRepository {

    Customer findById(int id);

    void save(Customer c);

    Customer getByName(String name);

    List<Customer> getAll();

}

