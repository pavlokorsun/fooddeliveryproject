package com.go2it.edu.service;

import com.go2it.edu.entity.Customer;
import com.go2it.edu.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer getByName(String name) {
        return customerRepository.getByName(name);
    }

    @Transactional
    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }


    @Transactional
    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

}