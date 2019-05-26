package com.go2it.edu.service;

import com.go2it.edu.entity.Goods;

import java.util.List;

public interface IGoodsService {

    Goods findById(int id);

    Goods getByName(String name);

    void save(Goods goods);

    List<Goods> getAll();
}
