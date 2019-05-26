package com.go2it.edu.repository;

import com.go2it.edu.entity.Parcels;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ParcelsRepository implements IParcelsRepository {
    //private final Logger logger = LoggerFactory.getLogger(com.go2it.edu.repository.MealRepository.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Parcels findById(int id) {
        return sessionFactory.getCurrentSession().find(Parcels.class, id);
    }

    @Override
    public void save(Parcels parcels) {
        if (parcels.getId() == 0) {
            sessionFactory.getCurrentSession().persist(parcels);
        } else {
            sessionFactory.getCurrentSession().merge(parcels);
        }
    }


    @Override
    public Parcels getByName(String name) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from parcels where name=:name");
            query.setParameter("name", name);
            Parcels parcels = (Parcels) query.getSingleResult();
            return parcels;
        } catch (NoResultException e) {
            //logger.warn("No meal(s) was found by the name: " + name);
            return null;
        }
    }

    @Override
    public List<Parcels> getAll() {
        TypedQuery<Parcels> query = sessionFactory.getCurrentSession().createQuery("SELECT p FROM parcels p", Parcels.class);
        return query.getResultList();
    }


}
