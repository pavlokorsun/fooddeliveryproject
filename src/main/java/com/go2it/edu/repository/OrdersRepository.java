package com.go2it.edu.repository;

import com.go2it.edu.entity.Meal;
import com.go2it.edu.entity.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrdersRepository implements IOrdersRepository {

    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Orders findById(int id) {
        return sessionFactory.getCurrentSession().find(Orders.class, id);
    }

    @Override
    public void save(Orders orders) {
        if (orders.getId() == 0) {
            sessionFactory.getCurrentSession().persist(orders);
        } else {
            sessionFactory.getCurrentSession().merge(orders);
        }
    }


    @Override
    public Orders getByName(String name) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from orders where status=:name");
            query.setParameter("name", name);
            Orders orders = (Orders) query.getSingleResult();
            return orders;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Orders> getAll() {
        TypedQuery<Orders> query = sessionFactory.getCurrentSession().createQuery("SELECT o FROM orders o", Orders.class);
        return query.getResultList();
    }






}
