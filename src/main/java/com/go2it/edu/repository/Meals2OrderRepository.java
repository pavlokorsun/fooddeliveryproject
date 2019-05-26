package com.go2it.edu.repository;


import com.go2it.edu.entity.Meals2Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class Meals2OrderRepository implements IMeals2OrderRepository {

    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Meals2Order findById(int id) {
        return sessionFactory.getCurrentSession().find(Meals2Order.class, id);
    }

    @Override
    public void save(Meals2Order meals2order) {
        if (meals2order.getId() == 0) {
            sessionFactory.getCurrentSession().persist(meals2order);
        } else {
            sessionFactory.getCurrentSession().merge(meals2order);
        }
    }


    @Override
    public Meals2Order getByOrderId(int order_id) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from meals2order where order_id=:order_id");
            query.setParameter("order_id", order_id);
            Meals2Order meals2order = (Meals2Order) query.getSingleResult();
            return meals2order;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Meals2Order> getAll() {
        TypedQuery<Meals2Order> query = sessionFactory.getCurrentSession().createQuery("SELECT mo FROM meals2order mo", Meals2Order.class);
        return query.getResultList();
    }

}
