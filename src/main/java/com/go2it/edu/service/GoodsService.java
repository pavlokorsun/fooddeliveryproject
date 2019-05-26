package com.go2it.edu.service;

import com.go2it.edu.entity.Goods;
import com.go2it.edu.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService implements IGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Transactional
    @Override
    public Goods findById(int id) {
        return goodsRepository.findById(id);
    }

    @Override
    public Goods getByName(String name) {
        return goodsRepository.getByName(name);
    }

    @Transactional
    @Override
    public void save(Goods goods) {
        goodsRepository.save(goods);
    }


    @Transactional
    @Override
    public List<Goods> getAll() {
        return goodsRepository.getAll();
    }

}
