package com.go2it.edu.service;


import com.go2it.edu.entity.Meals2Order;

import java.util.List;

public interface IMeals2OrderService {

    Meals2Order findById(int id);

    Meals2Order getByOrderId(int order_id);

    void save(Meals2Order meals2Order);

    List<Meals2Order> getAll();
}
