package com.hibernet.controller;

import com.hibernet.modelo.Cliente;

import com.hibernet.modelo.Incidentes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.file.Paths;


public class ClienteController {

    public void CreaCliente(Cliente c1) {

        String absolutePath = String.valueOf(Paths.get("C:\\Users\\TheDa\\OneDrive\\Arg Prog\\Tramo2\\TPT\\src\\main\\java\\org\\example\\hibernate.cfg.xml"));//Main.class.getClassLoader().Main.class.getClassLoader().getResource("org/example/hibernate.cfg.xml").getPath();
        Configuration configuration = new Configuration();
        configuration.configure(new File(absolutePath));


        SessionFactory sessionFactory = configuration.addAnnotatedClass(Cliente.class).addAnnotatedClass(Incidentes.class).buildSessionFactory();


       // SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = sessionFactory.openSession();



        try {
            session.beginTransaction();

            session.persist(c1);
            session.getTransaction().commit();


        } catch (Exception e) {

        }
        sessionFactory.close();
    }
}