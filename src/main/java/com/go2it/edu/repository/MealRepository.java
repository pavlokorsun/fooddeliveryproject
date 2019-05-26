package com.go2it.edu.repository;

import com.go2it.edu.entity.Meal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MealRepository implements IMealRepository {


    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Meal findById(int id) {
        return sessionFactory.getCurrentSession().find(Meal.class, id);
    }

    @Override
    public void save(Meal meal) {
        if (meal.getId() == 0) {
            sessionFactory.getCurrentSession().persist(meal);
        } else {
            sessionFactory.getCurrentSession().merge(meal);
        }
    }


    @Override
    public Meal getByName(String name) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from meal where name=:name");
            query.setParameter("name", name);
            Meal meal = (Meal) query.getSingleResult();
            return meal;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Meal> getAll() {
        TypedQuery<Meal> query = sessionFactory.getCurrentSession().createQuery("SELECT m FROM meal m", Meal.class);
        return query.getResultList();
    }




}





