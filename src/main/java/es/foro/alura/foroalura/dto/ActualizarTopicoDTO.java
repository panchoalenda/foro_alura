package es.foro.alura.foroalura.dto;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
  @NotNull
  String titulo,
  @NotNull
  String mensaje,
  @NotNull
  String autor,

  Boolean estado,
  @NotNull
    String curso,
  @NotNull
  Long usuarioCreador) {
}
