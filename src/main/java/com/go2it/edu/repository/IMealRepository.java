package com.go2it.edu.repository;

import com.go2it.edu.entity.Meal;

import java.util.List;

public interface IMealRepository {

    Meal findById(int id);

    void save(Meal m);

    Meal getByName(String name);

    List<Meal> getAll();

}
