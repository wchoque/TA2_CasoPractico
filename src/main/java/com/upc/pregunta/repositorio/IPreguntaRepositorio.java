package com.upc.pregunta.repositorio;

import com.upc.pregunta.entidades.Pregunta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPreguntaRepositorio extends ReactiveCrudRepository<Pregunta, Long> {
}
