package com.go2it.edu.repository;

import com.go2it.edu.entity.Goods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GoodsRepository implements IGoodsRepository {

    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Goods findById(int id) {
        return sessionFactory.getCurrentSession().find(Goods.class, id);
    }

    @Override
    public void save(Goods goods) {
        if (goods.getId() == 0) {
            sessionFactory.getCurrentSession().persist(goods);
        } else {
            sessionFactory.getCurrentSession().merge(goods);
        }
    }


    @Override
    public Goods getByName(String name) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from goods where name=:name");
            query.setParameter("name", name);
            Goods goods = (Goods) query.getSingleResult();
            return goods;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Goods> getAll() {
        try {
            TypedQuery<Goods> query = sessionFactory.getCurrentSession().createQuery("SELECT g FROM goods g", Goods.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


}
