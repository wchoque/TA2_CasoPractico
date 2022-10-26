package com.upc.salud.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "CENTRO_SALUD")
public class CentroSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nombre;
    private String tipo;
    private int calificacionInfraestructura;
    private int calificacionServicios;
    private int ambulancias;

    public CentroSalud(){}

    public CentroSalud(Long codigo, String nombre, String tipo, int calificacionInfraestructura, int calificacionServicios, int ambulancias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.calificacionInfraestructura = calificacionInfraestructura;
        this.calificacionServicios = calificacionServicios;
        this.ambulancias = ambulancias;
    }
}
