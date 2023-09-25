package es.foro.alura.foroalura.dto;

import es.foro.alura.foroalura.models.Respuesta;
import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;

public record DatosRespuestaDTO(Long id, String respuesta, Long idTopico, Long idUsuario) {
    public DatosRespuestaDTO(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getRespondido(), respuesta.getTopico().getId(), respuesta.getUsuario().getId());
    }
}
