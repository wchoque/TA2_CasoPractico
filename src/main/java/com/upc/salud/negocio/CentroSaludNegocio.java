package com.upc.salud.negocio;

import com.upc.salud.entidades.CentroSalud;
import com.upc.salud.repositorio.IMinisterioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CentroSaludNegocio implements  ICentroSaludNegocio{

    @Autowired
    IMinisterioRepositorio iMinisterioRepositorio;

    @Override
    public CentroSalud registrar(CentroSalud centroSalud) {
        return iMinisterioRepositorio.save(centroSalud);
    }

    @Override
    public List<CentroSalud> listar() {
        return iMinisterioRepositorio.findAll();
    }

    @Override
    public List<CentroSalud> listarConCalificaciones() {
        List<CentroSalud> listado;
        listado = iMinisterioRepositorio.findAll();

        for (CentroSalud centroSalud:listado) {
            centroSalud.setCalificacion(calcularCalificacion(centroSalud));
        }
        return listado;
    }

    @Override
    public CentroSalud buscar(Long codigo) throws Exception {
        return iMinisterioRepositorio.findById(codigo).orElseThrow(() -> new Exception("No se encontro Ministerio"));
    }

    @Override
    public List<CentroSalud> listarPorTipo(String tipo) {
        List<CentroSalud> listado;
        listado = iMinisterioRepositorio.findAll();
        var listadoFiltrado = new ArrayList<CentroSalud>();

        for (CentroSalud centroSalud:listado) {
            if (centroSalud.getTipo().contains(tipo)) {
                listadoFiltrado.add(centroSalud);
            }
        }
        return listadoFiltrado;
    }

    @Override
    public CentroSalud actualizar(CentroSalud centroSalud) throws Exception {
        buscar(centroSalud.getCodigo());
        return iMinisterioRepositorio.save(centroSalud);
    }

    @Override
    public double calcularCalificacion(CentroSalud centroSalud) {
        return centroSalud.getCalificacionInfraestructura() * 0.35 + centroSalud.getCalificacionServicios() * 0.65;
    }

    @Override
    public String obtenerResultadoFinal(CentroSalud centroSalud) {
        double calificacion = calcularCalificacion(centroSalud);
        return calificacion >= 80 ? "APROBADO" : "RECHAZADO";
    }
}
