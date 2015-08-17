package com.roofio;

import com.roofio.dbmanager.FilmManager;
import com.roofio.entity.Film;
import com.roofio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by air on 25/07/15.
 */
public class Main {
    public static void main(String[] args) {
        List<Film> all = new FilmManager().getAll();
        System.out.println(all);
    }
}
