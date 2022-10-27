package com.upc.salud.negocio;

import com.upc.salud.entidades.CentroSalud;

import java.util.List;

public interface ICentroSaludNegocio {
    public CentroSalud registrar(CentroSalud centroSalud); // 2)
    public List<CentroSalud> listar(); // 2)
    public List<CentroSalud> listarConCalificaciones(); // 3)
    public CentroSalud buscar(Long codigo) throws Exception;
    public List<CentroSalud> listarPorTipo(String tipo); // 3)
    public CentroSalud actualizar(CentroSalud centroSalud) throws Exception; // 6)

    public double calcularCalificacion(CentroSalud centroSalud); //1
    public String obtenerResultadoFinal(CentroSalud centroSalud); // 5




}
