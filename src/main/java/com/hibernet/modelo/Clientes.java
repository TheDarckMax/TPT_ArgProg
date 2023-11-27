package com.hibernet.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Table(name="lista_clientes")
@NoArgsConstructor
@ToString
@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Column(name="Lista-clientes")
    @ElementCollection
    private ArrayList<Cliente> clientes = new ArrayList<>();


    public void agregarCliente(Cliente c){

        if(!this.clientes.contains(c)) {
            this.clientes.add(c);
            System.out.println("Nuevo cliente cargado");
        }
        else System.out.println("Cliente ya cargado");
    }
}
