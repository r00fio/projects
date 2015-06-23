package com.roofio;

import com.roofio.bean.HelloService;
import com.roofio.dao.AnnotationDrivenFilmDao;
import com.roofio.dao.FilmDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        FilmDaoImpl filmDao = (FilmDaoImpl)context.getBean("filmDaoImpl");
        AnnotationDrivenFilmDao dao = (AnnotationDrivenFilmDao)context.getBean("annotationDrivenFilmDao");

        dao.insertFilm(null);
//        filmDao.findByCriteria("dfs",new Object());
    }
}
