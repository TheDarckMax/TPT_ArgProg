package com.hibernet.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Entity
@Table(name="Tecnicos")
@NoArgsConstructor
public class Tecnico {
    @Getter
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column(name="Nombre")
    private String nombre;
    @Getter
    @Column(name="Lista_Incidentes")
    @ElementCollection
    protected Incidentes listIncidentes = new Incidentes();

    @Column(name="Lista_Especialidades")
    @ElementCollection
    private ArrayList<Servicios> especialidades = new ArrayList<>();
    @Getter
    @Setter
    @Column(name="Disponibilidad")
    private boolean disponible;

    public boolean contiene_Servicio(Servicios servicio){
        return this.especialidades.contains(servicio);
    }

    public Tecnico(String nombre, Servicios servicios) {
        this.nombre = nombre;
        this.especialidades.add(servicios);
        this.disponible = true;
    }


    public Incidente resolverIncidente(Incidente inc) {
        inc.setEstado(Estados.Solucionado);
        inc.getTecnicoAsignado().setDisponible(true);
        return inc;
    }
}
