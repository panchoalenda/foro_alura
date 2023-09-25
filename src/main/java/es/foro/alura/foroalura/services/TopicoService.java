package es.foro.alura.foroalura.services;

import es.foro.alura.foroalura.dto.RespuestaDevueltaDTO;
import es.foro.alura.foroalura.models.Topico;

import java.util.List;
import java.util.Optional;

public interface TopicoService {

    List<Topico> listar();
    /*Optional<Topico> buscarPorId(Long id);*/
    Optional<Topico> buscarPorId(Long id);
    Topico guardar(Topico topico);
    Optional<Topico> actualizar(Long id, Topico topico);
    Optional<Topico> actualizarParaRespuesta(RespuestaDevueltaDTO respuestaDevueltaDTO);
    Optional<Topico> eliminar(Long id);



}
