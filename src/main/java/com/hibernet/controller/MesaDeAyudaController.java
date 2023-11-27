package com.hibernet.controller;

import com.hibernet.modelo.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class MesaDeAyudaController {

    public void IngresarIncidente(Cliente cliente, Servicios servicio, String descripcion, Tipos_Problemas tipo) {

        Date fecha = new Date();


        //SessionFactory sessionFactory = new Configuration().configure("org/example/hibernate.cfg.xml")
        String absolutePath = String.valueOf(Paths.get("C:\\Users\\TheDa\\OneDrive\\Arg Prog\\Tramo2\\TPT\\src\\main\\java\\org\\example\\hibernate.cfg.xml"));//Main.class.getClassLoader().Main.class.getClassLoader().getResource("org/example/hibernate.cfg.xml").getPath();
        Configuration configuration = new Configuration();
        configuration.configure(new File(absolutePath));


        SessionFactory sessionFactory = configuration.addAnnotatedClass(Clientes.class)
                .addAnnotatedClass(Incidente.class)
                .addAnnotatedClass(Cliente.class).addAnnotatedClass(Tecnico.class)
                .addAnnotatedClass(Clientes.class).addAnnotatedClass(Incidente.class).addAnnotatedClass(MesaDeAyuda.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incidente incidente = new Incidente(descripcion,tipo,fecha,servicio);
            //CriteriaQuery <Incidente> inc = session.getCriteriaBuilder().createQuery(Incidente.class);
            //inc.from(Incidente.class);
             //CriteriaQuery <Tecnico> tecnicoCriteriaQuery = session.getCriteriaBuilder().createQuery(Tecnico.class);

             //tecnicoCriteriaQuery.from(MesaDeAyuda.class);

            //List<Tecnico> tecnicos = session.createQuery(tecnicoCriteriaQuery).getResultList()

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tecnico> tecnicoCriteriaQuery = criteriaBuilder.createQuery(Tecnico.class);

            Root<MesaDeAyuda> mesaDeAyudaRoot = tecnicoCriteriaQuery.from(MesaDeAyuda.class);
            Join<MesaDeAyuda, Tecnicos> tecnicosJoin = mesaDeAyudaRoot.join("tecnicos"); // Ajusta el nombre del atributo si es necesario

            tecnicoCriteriaQuery.select(tecnicosJoin.<Tecnico>get("tecnico")); // Ajusta el nombre del atributo si es necesario

            List<Tecnico> tecnicos = session.createQuery(tecnicoCriteriaQuery).getResultList();

            Tecnico t = tecnicos.stream().filter(Tecnico -> Tecnico.isDisponible() && Tecnico.contiene_Servicio(servicio)).findFirst().orElse(null);
            //Tecnicos tecs = (Tecnicos) session.createQuery(tecnicoCriteriaQuery).getResultList();

            if(t != null){
            incidente.setTecnicoAsignado(t);
            incidente.setEstado(Estados.En_Proceso);
            t.setDisponible(false);
            t.getListIncidentes().agregarIncidente(incidente);
            session.update(t);
            session.getTransaction().commit();
            }else{
                incidente.setEstado(Estados.Esperando_Tecnico);

            }

            cliente.getListIncidentes().agregarIncidente(incidente);
            session.saveOrUpdate(cliente);
            session.persist(incidente);
            session.getTransaction().commit();



        }catch(Exception e){
            e.printStackTrace();
        }

        sessionFactory.close();



    }


}
