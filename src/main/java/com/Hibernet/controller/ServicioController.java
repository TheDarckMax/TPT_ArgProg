package com.Hibernet.controller;

import com.Hibernet.modelo.Servicio;
import jakarta.persistence.Id;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ServicioController {
    public String CreaServicio(String nombre){

        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Servicio.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try{
            Servicio servicio = new Servicio(nombre);
            session.beginTransaction();
            session.persist(servicio);
            session.getTransaction().commit();
            sessionFactory.close();

            return "Servicio creado: " + servicio.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Error en la carga de servicio";


    }

    public String QuitarServicio(int id){

        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Servicio.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try{

            session.beginTransaction();
            Servicio servicio = session.get(Servicio.class,id);
            session.remove(servicio);
            session.getTransaction().commit();
            sessionFactory.close();

            return "Servicio Eliminado: " + servicio.toString() + "ID :" + id;

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Error al eliminar un servicio";


    }

    public String ActualizarServ(int id, String nombre){
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Servicio.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try{

            session.beginTransaction();
            Servicio servicio = session.get(Servicio.class,id);
            servicio.setNombre(nombre);
            session.persist(servicio);
            session.getTransaction().commit();
            sessionFactory.close();

            return "Servicio ID: " + id + " Actualizado";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Error al actualizar los datos";

    }

    public String LeerServ(int id) {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Servicio.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();
            Servicio servicio = session.get(Servicio.class, id);
            session.getTransaction().commit();
            sessionFactory.close();

            return "Servicio ID: " + id + " " + servicio.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "Error en la lectura";

    }


}
