package com.roofio.dbmanager;

import com.roofio.entity.Film;
import com.roofio.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by air on 28/07/15.
 */
public class FilmManager {
    public List<Film> getAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Film as film");
        return query.list();
    }
}
