package com.go2it.edu.service;

import com.go2it.edu.entity.Staff;
import com.go2it.edu.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffService implements IStaffService {


    @Autowired
    private StaffRepository staffRepository;

    @Transactional
    @Override
    public Staff findById(int id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff getByName(String name) {
        return staffRepository.getByName(name);
    }

    @Transactional
    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }


    @Transactional
    @Override
    public List<Staff> getAll() {
        return staffRepository.getAll();
    }


}
