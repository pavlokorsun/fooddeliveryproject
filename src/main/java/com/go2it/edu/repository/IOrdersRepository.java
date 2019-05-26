package com.go2it.edu.repository;


import com.go2it.edu.entity.Orders;

import java.util.List;

public interface IOrdersRepository {

    Orders findById(int id);

    void save(Orders m);

    Orders getByName(String name);

    List<Orders> getAll();
}
