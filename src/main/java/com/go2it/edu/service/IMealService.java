package com.go2it.edu.service;

import com.go2it.edu.entity.Meal;

import java.util.List;

public interface IMealService {

    Meal findById(int id);

    Meal getByName(String name);

    void save(Meal meal);

    List<Meal> getAll();
}
