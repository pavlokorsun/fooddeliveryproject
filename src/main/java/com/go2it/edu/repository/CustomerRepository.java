package com.go2it.edu.repository;

import com.go2it.edu.entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CustomerRepository implements ICustomerRepository {


    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Customer findById(int id) {
        return sessionFactory.getCurrentSession().find(Customer.class, id);
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == 0) {
            sessionFactory.getCurrentSession().persist(customer);
        } else {
            sessionFactory.getCurrentSession().merge(customer);
        }
    }


    @Override
    public Customer getByName(String name) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from customer where name=:name");
            query.setParameter("name", name);
            Customer customer = (Customer) query.getSingleResult();
            return customer;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery("SELECT c FROM customer c", Customer.class);
        return query.getResultList();
    }

}

