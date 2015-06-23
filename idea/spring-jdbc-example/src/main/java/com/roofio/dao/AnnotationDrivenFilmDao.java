package com.roofio.dao;

import com.roofio.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * Created by bebe on 6/21/15.
 */
@Component
@Transactional
@Repository
public class AnnotationDrivenFilmDao {
    private static final String GET_ALL_FILMS = "SELECT * FROM film";
    private static final String INSERT_FILM = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features)\n" +
            "VALUES (\"Flowers in the atic\", \"Epic drama\", 2013, 1, 4, 1.22, 43, 43.33, \"PG\", \"Deleted Scenes\")";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void inti(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertFilm(Film film) {
        jdbcTemplate.execute(INSERT_FILM);
//        throw new RuntimeException();
    }

    public Film findByCriteria(String criteria, Object value) {
        throw new UnsupportedOperationException();
    }
}
