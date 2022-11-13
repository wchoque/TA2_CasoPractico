package com.upc.pregunta.negocio;

import com.upc.pregunta.entidades.Pregunta;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPreguntaNegocio {
    Flux<Pregunta> obtenerTodos();
    Mono<Pregunta> buscarPorId(long id);
    Flux<Pregunta> buscarConLimite(int limite);
    Mono<Pregunta> Registrar(Pregunta pregunta);
    Mono<ResponseEntity<Pregunta>> Actualizar(long id, Pregunta pregunta);
    Mono<Pregunta> Eliminar(long id);
}
