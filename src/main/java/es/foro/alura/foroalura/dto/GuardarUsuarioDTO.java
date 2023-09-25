package es.foro.alura.foroalura.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record GuardarUsuarioDTO(
  @NotNull
  String nombre,
  @NotNull
  String apellido,
  @NotNull
  @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
  String email,
  @NotNull
  String usuario) {
}
