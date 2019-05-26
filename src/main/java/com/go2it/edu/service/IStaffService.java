package com.go2it.edu.service;

import com.go2it.edu.entity.Staff;

import java.util.List;

public interface IStaffService {

    Staff findById(int id);

    Staff getByName(String name);

    void save(Staff staff);

    List<Staff> getAll();
}
