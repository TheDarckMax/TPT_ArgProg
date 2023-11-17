package com.Hibernet.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "servicios")
@NoArgsConstructor
@ToString
public class Servicio {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
private int id;
    @Column(name="nombre")
    @Getter
    @Setter
    private String nombre;

    public Servicio(String nombre) {

        this.nombre = nombre;
    }



}
