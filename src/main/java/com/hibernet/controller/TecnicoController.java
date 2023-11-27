package com.hibernet.controller;

import com.hibernet.modelo.Cliente;
import com.hibernet.modelo.Clientes;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class TecnicoController {


    public void ResolverIncidente(Cliente cliente){
        //SessionFactory sessionFactory = new Configuration().configure("org/example/hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();

        //Session session = sessionFactory.openSession();

        String absolutePath = String.valueOf(Paths.get("C:\\Users\\TheDa\\OneDrive\\Arg Prog\\Tramo2\\TPT\\src\\main\\java\\org\\example\\hibernate.cfg.xml"));//Main.class.getClassLoader().Main.class.getClassLoader().getResource("org/example/hibernate.cfg.xml").getPath();
        Configuration configuration = new Configuration();
        configuration.configure(new File(absolutePath));


        SessionFactory sessionFactory = configuration.addAnnotatedClass(Clientes.class).buildSessionFactory();
        Session session = sessionFactory.openSession();



        try{
            session.beginTransaction();
            CriteriaQuery<Cliente> cls = session.getCriteriaBuilder().createQuery(com.hibernet.modelo.Cliente.class);

            cls.from(Clientes.class);

            List<Cliente> clientes = session.createQuery(cls).getResultList();

            //if(!cls.getOrderList().contains(cliente)){
            if(!clientes.contains(cliente)){
                session.persist(cliente);
                System.out.println("Nuevo cliente cagardo x2");
            }else System.out.println("Cliente ya existente x2");

            session.getTransaction().commit();
            sessionFactory.close();



        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
