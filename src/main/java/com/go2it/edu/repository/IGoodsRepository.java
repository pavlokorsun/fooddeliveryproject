package com.go2it.edu.repository;

import com.go2it.edu.entity.Goods;

import java.util.List;

public interface IGoodsRepository {

    Goods findById(int id);

    void save(Goods g);

    Goods getByName(String name);

    List<Goods> getAll();
}
