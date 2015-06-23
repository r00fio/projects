package com.roofio.dao;

import com.roofio.entity.Film;
import org.springframework.stereotype.Component;

/**
 * Created by 8lackC on 6/14/15.
 */
@Component
public class FilmDaoImpl {
    private static final String GET_ALL_FILMS = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features)\n" +
            "VALUES (\"Flowers in the atic\", \"Epic drama\", 2013, 1, 4, 1.22, 43, 43.33, \"PG\", \"Deleted Scenes\");\n" +
            "DELETE FROM film\n" +
            "WHERE title = \"Flowers in the atic\";";

    public Film findByCriteria(String criteria, Object value) {

        return null;
    }

    public void insert(Film film) {

    }
}
