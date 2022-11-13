package com.upc.pregunta.entidades;

import org.springframework.data.annotation.Id;

public class Pregunta {
    @Id
    private long id;
    private String descripcion;
    private int orden;
    private int dificultad;

    public Pregunta(Long id, String descripcion, int orden, int dificultad) {
        this.id = id;
        this.descripcion = descripcion;
        this.orden = orden;
        this.dificultad = dificultad;
    }

    public Pregunta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}