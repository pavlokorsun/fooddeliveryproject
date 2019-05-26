package com.go2it.edu.repository;

import com.go2it.edu.entity.Staff;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class StaffRepository implements IStaffRepository {

    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Staff findById(int id) {
        return sessionFactory.getCurrentSession().find(Staff.class, id);
    }

    @Override
    public void save(Staff staff) {
        if (staff.getId() == 0) {
            sessionFactory.getCurrentSession().persist(staff);
        } else {
            sessionFactory.getCurrentSession().merge(staff);
        }
    }


    @Override
    public Staff getByName(String name) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from staff where name=:name");
            query.setParameter("name", name);
            Staff staff = (Staff) query.getSingleResult();
            return staff;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Staff> getAll() {
        TypedQuery<Staff> query = sessionFactory.getCurrentSession().createQuery("SELECT s FROM staff s", Staff.class);
        return query.getResultList();
    }

}