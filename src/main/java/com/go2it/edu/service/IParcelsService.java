package com.go2it.edu.service;

import com.go2it.edu.entity.Parcels;

import java.util.List;

public interface IParcelsService {

    Parcels findById(int id);

    Parcels getByName(String name);

    void save(Parcels parcels);

    List<Parcels> getAll();
}
