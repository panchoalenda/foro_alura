package es.foro.alura.foroalura.dto;

import es.foro.alura.foroalura.models.Topico;

import java.time.LocalDateTime;

public record ListarTopicoDTO(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean estado, String autor, String curso) {
    public ListarTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
    }

}
