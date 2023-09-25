package es.foro.alura.foroalura.dto;

import es.foro.alura.foroalura.models.Respuesta;

public record ListarRespuestaDTO(Long id, String respuesta, Long idTopico, Long idUsuario) {

    public ListarRespuestaDTO(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getRespondido(), respuesta.getTopico().getId(), respuesta.getUsuario().getId());
    }
}
