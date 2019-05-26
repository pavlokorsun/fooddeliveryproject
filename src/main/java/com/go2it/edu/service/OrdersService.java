package com.go2it.edu.service;


import com.go2it.edu.entity.Orders;
import com.go2it.edu.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Transactional
    @Override
    public Orders findById(int id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders getByName(String name) {
        return ordersRepository.getByName(name);
    }

    @Transactional
    @Override
    public void save(Orders orders) {
        ordersRepository.save(orders);
    }


    @Transactional
    @Override
    public List<Orders> getAll() {
        return ordersRepository.getAll();
    }


}
