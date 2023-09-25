package es.foro.alura.foroalura.services;

import es.foro.alura.foroalura.models.Respuesta;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RespuestaService {

    List<Respuesta> listar();
    Page<Respuesta> listarPageable(Pageable pageable);
    Optional<Respuesta> buscarPorId(Long id);
    Respuesta guardar(Respuesta respuesta);
    Optional<Respuesta> actualizar(Long id, Respuesta respuesta);
    Optional<Respuesta> eliminar(Long id);



}
