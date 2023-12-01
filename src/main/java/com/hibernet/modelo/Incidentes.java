package com.hibernet.modelo;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;

@Entity
public class Incidentes{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Column(name="Lista_incidentes")
    private ArrayList<Incidente> incidentes = new ArrayList<>();


    public Incidentes(){}


    public void agregarIncidente(Incidente incidente){
        this.incidentes.add(incidente);
    }

    public Incidente primerIncidente(){
        return this.incidentes.stream().findFirst().get();
    }

    public Incidente getIncPos(int i){
        try {
            return this.incidentes.get(i);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Error, posicion inexistente");
            return null;
        }
    }

    public Incidente getIncidenteTec_Est(Tecnico t, Estados estados){
        return this.incidentes.stream().filter(Incidente ->
                Incidente.getTecnicoAsignado().getNombre().equals(t.getNombre()) && Incidente.getEstado().equals(estados))
                .findFirst().orElse(null);

    }
    public Incidente getIncidente_Est(Estados estados){
        try {
            return this.incidentes.stream().filter(Incidente -> Incidente.getEstado().equals(estados)).findFirst().get();
        }catch(NullPointerException e){
            System.out.println("Error, No se encontraron resultados");
            return null;
        }
    }

    public void mostrar_Estados_Incidentes(){
        for (Incidente x : this.incidentes) {
            System.out.println( x.getEstado() + "\n");
        }
    }
    public void MostrarIncidentes(){
        System.out.println("\n");
        for(int i = 0; i < this.incidentes.size();i++){
            System.out.println("Estado: " + this.incidentes.get(i).getEstado());
            System.out.println("Tecnico asignado: " + this.incidentes.get(i).getTecnicoAsignado().getNombre() + "\n");
        }
        System.out.println("\n");
    }


}
