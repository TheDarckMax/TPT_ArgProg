package com.hibernet.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name="MesaDeAyuda")
@NoArgsConstructor
public class MesaDeAyuda {
    @Column(name="Lista_Tecnicos")
    @ElementCollection
    private Tecnicos tecnicos = new Tecnicos() ;
    @Getter
    @Column(name="Lista_Incidentes")
    @ElementCollection
    private Incidentes listaIncidentes = new Incidentes();
    @Getter
    @Column(name="Lista_Clientes")
    @ElementCollection
    private Clientes clientes = new Clientes();
    public void ingresarIncidente(Cliente cliente, Servicios servicio, String descripcion, Tipos_Problemas tipo) {

        Date fecha = new Date();
        Incidente incidente = new Incidente(descripcion,tipo,fecha,servicio);
        try {
            asignarTecnico(tecnicoDisp(servicio),incidente);
            incidente.getTecnicoAsignado().listIncidentes.agregarIncidente(incidente); //agrego un incidente a la lista del tecnico
            System.out.println("Tecnico : " + incidente.getTecnicoAsignado().getNombre() + " Asignado al problema");
        }catch(NullPointerException e){
            System.out.println("No hay tecnicos disponible");
            incidente.setEstado(Estados.Esperando_Tecnico);
        }
        this.clientes.agregarCliente(cliente);
        cliente.getListIncidentes().agregarIncidente(incidente);
        this.listaIncidentes.agregarIncidente(incidente);

    }

    public Tecnico tecnicoDisp(Servicios servicio){
        //agregar el tecnico que coincida con alguna especialidad
        Tecnico t = this.tecnicos.getTecnicos().stream().filter(tecnico -> tecnico.contiene_Servicio(servicio)).findFirst().orElse(null);
        if( t != null && t.isDisponible()){
            System.out.println("Tecnico Disponible");
            return  t;
        }else {
            System.out.println("Error, Tecnico no disponible");
            return null;
        }
    }
    public void agregaPersonal(Tecnico t){
        if(!this.tecnicos.getTecnicos().contains(t)) this.tecnicos.getTecnicos().add(t);
        else System.out.println("Error, Tecnico ya existente");
    }


    public void asignarTecnico(Tecnico tecnico, Incidente incidente) {
        if(tecnico.contiene_Servicio(incidente.getServicio())) {
            incidente.setTecnicoAsignado(tecnico);
            incidente.setEstado(Estados.En_Proceso);
            tecnico.setDisponible(false);
            System.out.println("Tecnico en trabajo");
        }else {
            System.out.println("Tecnico no calificado para ello");
        }
    }

    public void mostrarTecnicos(){
        for (Tecnico x : this.tecnicos.getTecnicos()) {
            System.out.println("Tecnico: " + x.getNombre() + "\n" );
        }
    }

}
