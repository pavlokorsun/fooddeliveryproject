package com.go2it.edu.repository;

import com.go2it.edu.entity.Meals2Order;

import java.util.List;

public interface IMeals2OrderRepository {

    Meals2Order findById(int id);

    void save(Meals2Order mo);

    Meals2Order getByOrderId(int order_id);

    List<Meals2Order> getAll();

}
