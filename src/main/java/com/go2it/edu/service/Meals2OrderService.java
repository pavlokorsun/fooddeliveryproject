package com.go2it.edu.service;


import com.go2it.edu.entity.Meals2Order;
import com.go2it.edu.repository.Meals2OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Meals2OrderService implements IMeals2OrderService {

    @Autowired
    private Meals2OrderRepository meals2OrderRepository;

    @Transactional
    @Override
    public Meals2Order findById(int id) {
        return meals2OrderRepository.findById(id);
    }

    @Override
    public Meals2Order getByOrderId(int order_id) {
        return meals2OrderRepository.getByOrderId(order_id);
    }

    @Transactional
    @Override
    public void save(Meals2Order meals2Order) {
        meals2OrderRepository.save(meals2Order);
    }


    @Transactional
    @Override
    public List<Meals2Order> getAll() {
        return meals2OrderRepository.getAll();
    }


}
