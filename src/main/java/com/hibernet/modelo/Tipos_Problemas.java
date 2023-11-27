package com.hibernet.modelo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Tipos_Problemas {
    @Enumerated(EnumType.STRING)
    Hardware, Software, Red, Otros
}
