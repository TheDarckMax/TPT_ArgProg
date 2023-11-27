package com.hibernet.modelo;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.util.Date;
@Entity
@Table(name="incidente")
public class Incidente {
    @Getter
    @Setter
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column(name="servicio")
    private Servicios servicio;
    @Getter
    @Setter
    @Column(name="descripcion")
    private String descripcion;
    @Getter
    @Setter
    @Column(name="tipo_Problema")
    private Tipos_Problemas tipo;
    @Getter
    @Setter
    @Column(name="estado")
    private Estados estado;
    @Getter
    @Setter
    @Column(name="fecha_reporte")
    private Date fechaReporte;
    @Getter
    @Setter
    @Column(name="fecha_resolucion")
    private Date fechaResolucion;
    @Getter
    @Setter
    @Column(name="tenico_asignado")
    private Tecnico tecnicoAsignado;


    public Incidente(){
    }

    public Incidente(String descripcion, Tipos_Problemas tipo, Date fechaReporte, Servicios servicio) {
        this.descripcion = descripcion;
        this.tipo = tipo; // Puede ser "Hardware", "Software", "Red", u otro valor según sea necesario
        this.fechaReporte = fechaReporte;
        this.servicio = servicio;
    }

    public boolean isCoincidencia_Serv_fecha(Incidente inc,Date fecha, Servicios serv){
            return inc.fechaResolucion.equals(fecha) && inc.servicio.equals(serv);
    }


}
