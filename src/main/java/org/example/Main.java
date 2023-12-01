package org.example;
import com.hibernet.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

import static com.hibernet.modelo.Servicios.Windows_Sup;


public class Main {
    public static void main(String[] args) {

        MesaDeAyuda mesa = new MesaDeAyuda();

        //Testeando datos
        System.out.println("Testing");
        Cliente c1 = new Cliente("Pepito","pepe@hotmail.com","08004444");
        Tecnico t1 = new Tecnico("Robert Deniro", Windows_Sup);

        mesa.agregaPersonal(t1);
        mesa.ingresarIncidente(c1, Windows_Sup,"Se quemo todo", Tipos_Problemas.Hardware);
        Tecnico t2 = new Tecnico("Estevan Quito", Windows_Sup);
        mesa.agregaPersonal(t2);

        mesa.ingresarIncidente(c1, Windows_Sup,"Prefiero Linux",Tipos_Problemas.Red);

        mesa.asignarTecnico(t2,c1.getListIncidentes().getIncidente_Est(Estados.Esperando_Tecnico));

        System.out.println("fin Testing\n");
       //Testeando datos
        mesa.MenuPrincipal();



        /*
        MesaDeAyudaController mesaDeAyudaController = new MesaDeAyudaController();

        TecnicoController tecnicoController = new TecnicoController();

        ClientesController clientesController = new ClientesController();

        ClienteController clienteController = new ClienteController();
        clienteController.CreaCliente(c1);
        clientesController.AgregaCliente(c1);

       mesaDeAyudaController.IngresarIncidente(c1,Servicios.Windows_Sup,"piola",Tipos_Problemas.Hardware);

        //No puedo guardar nada en la base de datos por que hibernate no me reconoce las Entidades
*/





    }


        }


