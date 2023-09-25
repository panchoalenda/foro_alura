package es.foro.alura.foroalura.dto;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuarioDTO(
  @NotNull
  String nombre,
  @NotNull
  String apellido,
  @NotNull
  String email,
  @NotNull
  String usuario) {
}
