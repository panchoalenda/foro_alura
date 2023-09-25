package es.foro.alura.foroalura.dto;

import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;

import java.time.LocalDateTime;

public record ListarUsuarioDTO(Long id, String nombre, String apellido, String email, String usuario) {
    public ListarUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getUsuario());
    }

}
