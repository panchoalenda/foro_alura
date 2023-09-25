package es.foro.alura.foroalura.dto;

import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;
import jakarta.validation.constraints.NotNull;

public record GuardarRespuestaDTO(
  @NotNull
  String respuesta,
  @NotNull
  Topico topico,
  @NotNull
  Usuario usuario) {
}

