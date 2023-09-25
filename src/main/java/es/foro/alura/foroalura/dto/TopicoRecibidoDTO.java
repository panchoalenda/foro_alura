package es.foro.alura.foroalura.dto;

import jakarta.validation.constraints.NotNull;

public record TopicoRecibidoDTO(
  @NotNull
  String titulo,
  @NotNull
  String mensaje,
  @NotNull
  String autor,
  @NotNull
  String curso,
  @NotNull
  Long usuario) {

}
