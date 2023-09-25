package es.foro.alura.foroalura.services;

import es.foro.alura.foroalura.models.Usuario;
import es.foro.alura.foroalura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> actualizar(Long id, Usuario usuario) {
        Usuario usuarioBd = null;
        Optional<Usuario> o = this.buscarPorId(id);

        if (o.isPresent()) {
            usuarioBd = o.get();
            usuarioBd.setNombre(usuario.getUsuario());
            usuarioBd.setApellido(usuario.getUsuario());
            usuarioBd.setEmail(usuario.getUsuario());
            usuarioBd.setUsuario(usuario.getUsuario());
            usuarioRepository.save(usuarioBd);
        }
        return Optional.ofNullable(usuarioBd);
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminar(Long id) {
        Optional<Usuario> o = this.buscarPorId(id);
        if (o.isPresent()) {
            usuarioRepository.deleteById(id);
        }
        return Optional.empty();
    }
}
