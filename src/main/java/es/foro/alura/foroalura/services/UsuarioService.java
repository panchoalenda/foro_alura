package es.foro.alura.foroalura.services;

import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();
    Optional<Usuario> buscarPorId(Long id);
    Usuario guardar(Usuario usuario);
    Optional<Usuario> actualizar(Long id, Usuario usuario);
    Optional<Usuario> eliminar(Long id);

}
