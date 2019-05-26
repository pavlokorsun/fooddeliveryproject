package com.go2it.edu.service;

import com.go2it.edu.entity.Parcels;
import com.go2it.edu.repository.ParcelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcelsService implements IParcelsService{

    @Autowired
    private ParcelsRepository parcelsRepository;

    @Transactional
    @Override
    public Parcels findById(int id) {
        return parcelsRepository.findById(id);
    }

    @Override
    public Parcels getByName(String name) {
        return parcelsRepository.getByName(name);
    }

    @Transactional
    @Override
    public void save(Parcels parcels) {
        parcelsRepository.save(parcels);
    }


    @Transactional
    @Override
    public List<Parcels> getAll() {
        return parcelsRepository.getAll();
    }

}
