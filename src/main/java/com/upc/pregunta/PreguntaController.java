package com.upc.pregunta;

import com.upc.pregunta.entidades.Pregunta;
import com.upc.pregunta.negocio.IPreguntaNegocio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("api/preguntas")
public class PreguntaController {
    Logger logger = LoggerFactory.getLogger(PreguntaController.class);

    @Autowired
    IPreguntaNegocio _preguntaNegocio;

    @GetMapping(value = "/obtenerTodos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Pregunta> obtenerTodos() {
        try {
            return _preguntaNegocio.obtenerTodos();
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }

    @GetMapping(value = "/filtrado/{limite}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Pregunta> buscarConLimite(@PathVariable int limite){
        try {
            logger.info("Test logging");
            return _preguntaNegocio.buscarConLimite(limite);
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }

    @PostMapping("/registrar")
    public Mono<Pregunta> registrar(@RequestBody Pregunta pregunta) {
        try {
            return _preguntaNegocio.Registrar(pregunta).log();
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Mono<Pregunta> buscarPorId(@PathVariable Long id){
        try {
            return _preguntaNegocio.buscarPorId(id);
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Pregunta>>  actualizar(@PathVariable Long id , @RequestBody Pregunta pregunta) {
        try {
            return _preguntaNegocio.Actualizar(id, pregunta);
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable long id){
        try {
            return _preguntaNegocio.Eliminar(id)
                    .map(r -> ResponseEntity.ok().<Void>build())
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }

    @GetMapping(value = "/obtenerpreguntas/{cantidad}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Pregunta> obtenerPreguntas(@PathVariable int cantidad){
        try {
            return _preguntaNegocio.buscarConLimite(cantidad);
        } catch (Exception e){
            logger.error("Hubo un error!", e);
        }
        return null;
    }
}