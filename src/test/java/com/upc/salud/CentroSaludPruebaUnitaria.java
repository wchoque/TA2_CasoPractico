package com.upc.salud;

import com.upc.salud.entidades.CentroSalud;
import com.upc.salud.negocio.ICentroSaludNegocio;
import com.upc.salud.repositorio.IMinisterioRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CentroSaludPruebaUnitaria {

    @Autowired
    private ICentroSaludNegocio iCentroSaludNegocio;
    @MockBean
    private IMinisterioRepositorio iMinisterioRepositorio;

    @Test
    void testCalcularCalificacion(){
        CentroSalud centroSalud = new CentroSalud(1L, "GRAU", "HOSPITAL", 75, 90, false);
        when(iMinisterioRepositorio.findById(1L)).thenReturn(Optional.of(centroSalud));
        Assertions.assertEquals(84.75,iCentroSaludNegocio.calcularCalificacion(centroSalud));
    }

    @Test
    void testListarTodos(){
        when(iMinisterioRepositorio.findAll()).thenReturn(
                Stream.of(
                        new CentroSalud(2L, "ALMENARA", "HOSPITAL", 20, 70, true),
                        new CentroSalud(3L, "GOOD HOPE", "CLINICA", 80, 90, true),
                        new CentroSalud(4L, "ANGLOAMERICANA", "CLINICA", 50, 40, false)
                ).collect(Collectors.toList()));
        Assertions.assertEquals(3, iCentroSaludNegocio.listar().size());
    }



    @Test
    void testListConCalificaciones(){
        when(iMinisterioRepositorio.findAll()).thenReturn(
                Stream.of(
                        new CentroSalud(1L, "ALMENARA", "HOSPITAL", 20, 70, true),
                        new CentroSalud(2L, "GOOD HOPE", "CLINICA", 80, 90, true),
                        new CentroSalud(3L, "ANGLOAMERICANA", "HOSPITAL", 50, 40, false),
                        new CentroSalud(4L, "GRAU", "HOSPITAL", 60, 50, false)

                ).collect(Collectors.toList()));
        List<CentroSalud> listado;
        listado = iCentroSaludNegocio.listarConCalificaciones();
        for (CentroSalud centroSalud:listado){
            System.out.println(centroSalud.getCodigo() + "  " + centroSalud.getNombre() + "  " +
                    centroSalud.getCalificacion());
        }
        Assertions.assertEquals(4, iCentroSaludNegocio.listar().size());
    }

    @Test
    void testObtenerResultadoFinalAprobado(){
        CentroSalud centroSalud = new CentroSalud(1L, "GRAU", "HOSPITAL", 75, 90, false);
        when(iMinisterioRepositorio.findById(1L)).thenReturn(Optional.of(centroSalud));
        Assertions.assertEquals("APROBADO",iCentroSaludNegocio.obtenerResultadoFinal(centroSalud));
    }

    @Test
    void testObtenerResultadoFinalDesaprobado(){
        CentroSalud centroSalud = 	new CentroSalud(2L, "ANGLOAMERICANA", "CLINICA", 50, 40, false);
        when(iMinisterioRepositorio.findById(1L)).thenReturn(Optional.of(centroSalud));
        Assertions.assertEquals("DESAPROBADO",iCentroSaludNegocio.obtenerResultadoFinal(centroSalud));
    }
}
