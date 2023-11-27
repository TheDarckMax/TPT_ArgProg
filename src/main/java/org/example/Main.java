package org.example;
import com.hibernet.modelo.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Ejemplo de uso
        MesaDeAyuda mesa = new MesaDeAyuda();
        Cliente c1 = new Cliente("Pepito","pepe@hotmail.com","08004444");
        Tecnico t1 = new Tecnico("Robert Deniro",Servicios.Windows_Sup);

        mesa.agregaPersonal(t1);
        mesa.ingresarIncidente(c1,Servicios.Windows_Sup,"Se quemo todo", Tipos_Problemas.Hardware);
        Tecnico t2 = new Tecnico("Estevan Quito",Servicios.Windows_Sup);
        mesa.agregaPersonal(t2);

        System.out.println("Tecnico disponible ? :" + t1.isDisponible());

        mesa.ingresarIncidente(c1,Servicios.Windows_Sup,"Prefiero Linux",Tipos_Problemas.Red);

        mesa.asignarTecnico(t2,c1.getListIncidentes().getIncidente_Est(Estados.Esperando_Tecnico));


        System.out.println("Tecnico asignado al segundo problema: " + c1.getListIncidentes().
                getIncidenteTec_Est(t2,Estados.En_Proceso).getTecnicoAsignado().getNombre());


        System.out.println("Estado del incidente: "  + c1.getListIncidentes().getIncidenteTec_Est(t2,Estados.En_Proceso)
                .getEstado() + " Tecnico: " + c1.getListIncidentes().getIncidenteTec_Est(t2,Estados.En_Proceso)
                .getTecnicoAsignado().getNombre());

        mesa.getListaIncidentes().mostrar_Estados_Incidentes();
        mesa.mostrarTecnicos();

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