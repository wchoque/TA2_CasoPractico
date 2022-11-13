package com.upc.pregunta.negocio;

import com.upc.pregunta.entidades.Pregunta;
import com.upc.pregunta.repositorio.IPreguntaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class PreguntaNegocio implements IPreguntaNegocio {
    @Autowired
    IPreguntaRepositorio _preguntaRepositorio;

    @Override
    public Flux<Pregunta> obtenerTodos() {
        //return Flux.fromIterable(_preguntaRepositorio.findAll());
        return _preguntaRepositorio.findAll()
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @Override
    public Mono<Pregunta> buscarPorId(long id) {
        return _preguntaRepositorio.findById(id);
    }

    @Override
    public Flux<Pregunta> buscarConLimite(int limite) {
        var preguntas = _preguntaRepositorio.findAll().delayElements(Duration.ofSeconds(1)).log();
        return preguntas.limitRequest(limite);
    }

    @Override
    public Mono<Pregunta> Registrar(Pregunta pregunta) {
        return _preguntaRepositorio.save(pregunta).log();
    }

    @Override
    public Mono<ResponseEntity<Pregunta>> Actualizar(long id, Pregunta pregunta) {
        return _preguntaRepositorio.findById(id)
                .flatMap(preguntaDb -> {
                    preguntaDb.setDescripcion(pregunta.getDescripcion());
                    preguntaDb.setOrden(pregunta.getOrden());
                    preguntaDb.setDificultad(pregunta.getDificultad());
                    return _preguntaRepositorio.save(preguntaDb);
                })
                .map(preguntaDb -> new ResponseEntity<>(preguntaDb, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    @Override
    public Mono<Pregunta> Eliminar(long id) {
        return _preguntaRepositorio.findById(id)
                .flatMap(pregunta -> _preguntaRepositorio.delete(pregunta)
                        .then(Mono.just(pregunta)));
    }
}
