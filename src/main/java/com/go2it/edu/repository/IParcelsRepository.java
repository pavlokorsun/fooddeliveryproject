package com.go2it.edu.repository;

import com.go2it.edu.entity.Parcels;

import java.util.List;

public interface IParcelsRepository {

    Parcels findById(int id);

    void save(Parcels p);

    Parcels getByName(String name);

    List<Parcels> getAll();

}
