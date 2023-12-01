package com.hibernet.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.hibernet.modelo.Servicios.Windows_Sup;
import static java.lang.System.in;

@Entity
@Table(name="MesaDeAyuda")
@NoArgsConstructor
public class MesaDeAyuda {
    @Column(name="Lista_Tecnicos")
    @ElementCollection
    @Getter
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



    public void MenuPrincipal(){
        MesaDeAyuda mesa = this;
        System.out.println("Bienvenido al sistema de gestion general");
        ArrayList<Servicios> servicios = new ArrayList<>();
        servicios.add(Windows_Sup);
        servicios.add(Servicios.Linux_Sup);
        servicios.add(Servicios.MacOs_Sup);
        ArrayList<Tipos_Problemas> problemas = new ArrayList<>();
        problemas.add(Tipos_Problemas.Hardware);
        problemas.add(Tipos_Problemas.Red);
        problemas.add(Tipos_Problemas.Software);
        problemas.add(Tipos_Problemas.Otros);
        Scanner scanner = new Scanner(in);
        ArrayList<Cliente> clientes = new ArrayList<>();

        ArrayList<Tecnico> tecnicos = new ArrayList<>();
        ArrayList<Incidente> incidentes = new ArrayList<>();

        int opcion;
        do {
            System.out.println("------ Menú Principal ------");
            System.out.println("1. Carga de clientes");
            System.out.println("2. Carga de tecnicos");
            System.out.println("3. Muestra de clientes");
            System.out.println("4. Muestra de tecnicos");
            System.out.println("5. Menú Incidentes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la razon social del cliente: ");
                    String razonScl = scanner.next();
                    System.out.println("Ingrese el Email del cliente: ");
                    String email = scanner.next();
                    System.out.println("Ingrese el telefono del cliente: ");
                    String telf = scanner.next();
                    Cliente cliente = new Cliente(razonScl,email,telf);
                    mesa.getClientes().agregarCliente(cliente);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del tecnico: ");
                    String nombre = scanner.next();
                    System.out.println("Seleccione el tipo de servicio asociado al tecnico: ");
                    do {
                        System.out.println("1- Windows_Sup");
                        System.out.println("2- MacOs_Sup");
                        System.out.println("3- Linux_Sup");
                        opcion = scanner.nextInt();
                    }while(opcion != 1 && opcion!=2 && opcion!=3) ;
                    Tecnico tecnico = new Tecnico(nombre,servicios.get(opcion-1));
                    mesa.agregaPersonal(tecnico);
                    break;
                case 3:
                    mesa.getClientes().Mostrarclientes();
                    break;
                case 4:
                    mesa.mostrarTecnicos();
                    break;
                case 5:

                    int opcionIncidentes;
                    do {
                        System.out.println("------ Menú Incidentes ------");
                        System.out.println("1. Carga de incidente");
                        System.out.println("2. Mostrar todos los incidentes");
                        System.out.println("3. Resolver un incidente");
                        System.out.println("4. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        opcionIncidentes = scanner.nextInt();

                        switch (opcionIncidentes) {
                            case 1:
                                Cliente x = new Cliente();
                                System.out.println("Seleccione el Cliente que informa el Incidente: ");
                                mesa.getClientes().Mostrarclientes();
                                int cl = scanner.nextInt();
                                System.out.println("Seleccione el tipo de servicio asociado al Incidente: ");
                                do {
                                    System.out.println("1- Windows_Sup");
                                    System.out.println("2- MacOs_Sup");
                                    System.out.println("3- Linux_Sup");
                                    opcion = scanner.nextInt();
                                }while(opcion != 1 && opcion!=2 && opcion!=3) ;
                                System.out.println("Seleccione el Tecnico para dicho problema: ");
                                mesa.mostrarTecnicos();
                                int tc = scanner.nextInt()-1;
                                System.out.println("Seleccione el tipo de problema asociado al Incidente: ");
                                do {
                                    System.out.println("1- " + Tipos_Problemas.Red);
                                    System.out.println("2- " + Tipos_Problemas.Hardware);
                                    System.out.println("3- " + Tipos_Problemas.Software);
                                    System.out.println("4- " + Tipos_Problemas.Otros);
                                    opcion = scanner.nextInt();
                                }while(opcion != 1 && opcion!=2 && opcion!=3 && opcion!=4) ;

                                mesa.ingresarIncidente(mesa.getClientes().getClientes().get(cl-1),servicios.get(opcion-1),
                                        "",problemas.get(opcion-1));
                                break;
                            case 2:
                                mesa.listaIncidentes.MostrarIncidentes();
                                break;
                            case 3:
                                System.out.println("Seleccione el Incidente a resolver: ");
                                mesa.getListaIncidentes().MostrarIncidentes();
                                int incd = scanner.nextInt()-1;
                                mesa.getListaIncidentes().getIncPos(incd).getTecnicoAsignado().
                                        resolverIncidente(mesa.getListaIncidentes().getIncPos(incd));
                                break;
                            case 4:
                                System.out.println("Volviendo al menú principal...");
                                break;
                            default:
                                System.out.println("Opción no válida. Inténtelo de nuevo.");
                        }
                    } while (opcionIncidentes != 4);
                    break;
                case 6:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }

}
