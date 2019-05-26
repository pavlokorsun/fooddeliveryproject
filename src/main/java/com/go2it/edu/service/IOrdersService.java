package com.go2it.edu.service;


import com.go2it.edu.entity.Orders;

import java.util.List;

public interface IOrdersService {
    Orders findById(int id);

    Orders getByName(String name);

    void save(Orders orders);

    List<Orders> getAll();
}
