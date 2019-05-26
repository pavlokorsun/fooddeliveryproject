package com.go2it.edu.repository;

import com.go2it.edu.entity.Staff;

import java.util.List;

public interface IStaffRepository {

    Staff findById(int id);

    void save(Staff s);

    Staff getByName(String name);

    List<Staff> getAll();

}
