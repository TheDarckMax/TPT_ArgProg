package com.hibernet.modelo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public enum Estados {
    @Enumerated(EnumType.STRING)
    Pendiente, En_Proceso, Solucionado, No_Solucionado, Esperando_Tecnico
}
