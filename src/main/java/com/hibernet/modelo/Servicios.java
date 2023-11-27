package com.hibernet.modelo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Servicios {
    @Enumerated(EnumType.STRING)
    Windows_Sup, MacOs_Sup, Linux_Sup
}
