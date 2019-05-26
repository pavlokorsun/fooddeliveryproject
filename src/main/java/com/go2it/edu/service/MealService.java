package com.go2it.edu.service;


import com.go2it.edu.entity.Meal;
import com.go2it.edu.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MealService implements IMealService {

    @Autowired
    private MealRepository mealRepository;

    @Transactional
    @Override
    public Meal findById(int id) {
        return mealRepository.findById(id);
    }

    @Override
    public Meal getByName(String name) {
        return mealRepository.getByName(name);
    }

    @Transactional
    @Override
    public void save(Meal meal) {
        mealRepository.save(meal);
    }


    @Transactional
    @Override
    public List<Meal> getAll() {
        return mealRepository.getAll();
    }

}

