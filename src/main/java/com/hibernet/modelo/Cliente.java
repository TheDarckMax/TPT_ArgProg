package com.hibernet.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;





@Entity
@Table(name="Clientes")
public class Cliente{

    @Getter
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column(name="razon_social")
    private String raz_soc;
    @Getter
    @Setter
    @Column(name="email")
    private String email;
    @Getter
    @Setter
    @Column(name="telefono")
    private String telefono;




    @Setter
    @Getter
    private Incidentes listIncidentes = new Incidentes();

    @ElementCollection
    @Column(name="servicios")
    private ArrayList<Servicios> servicios = new ArrayList<>();

    public Cliente (){}

    public Cliente(String nombre, String email, String telefono) {
        this.raz_soc = nombre;
        this.email = email;
        this.telefono = telefono;
    }


}
