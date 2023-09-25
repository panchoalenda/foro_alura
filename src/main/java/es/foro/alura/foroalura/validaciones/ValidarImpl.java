package es.foro.alura.foroalura.validaciones;

import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;
import es.foro.alura.foroalura.repository.RespuestaRepository;
import es.foro.alura.foroalura.repository.TopicoRepository;
import es.foro.alura.foroalura.repository.UsuarioRepository;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarImpl implements Validador {
    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RespuestaRepository respuestaRepository;

    @Override
    public void validarTituloMensaje(String titulo, String mensaje) {

        Optional<Long> idTituloOptional = topicoRepository.findTituloById(titulo);
        Optional<Long> idMensajeOptional = topicoRepository.findMensajeById(mensaje);

        if (idTituloOptional.isPresent() && idMensajeOptional.isPresent()) {
            throw new RuntimeException("El título o el mensaje ya existe");
        }
    }

    @Override
    public void validarUsuario(String nombre, String apellido, String email, String usuario) {
        Optional<Long> idNombreApellidoOptional = usuarioRepository.findNombreAndApellidoById(nombre, apellido);
        Optional<Long> idEmailjeOptional = usuarioRepository.findEmailById(email);
        Optional<Long> idUsuariojeOptional = usuarioRepository.findUsuarioById(usuario);

        if (idNombreApellidoOptional.isPresent()) {
            throw new RuntimeException("El nombre y el apellido ya están Registrados");
        }
        if (idEmailjeOptional.isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (idUsuariojeOptional.isPresent()) {
            throw new RuntimeException("El usuario ya está registrado");
        }
    }

    public void validarUsuario(Long id) {
        if (id == 0) {
            throw new RuntimeException("Debe agregar el id del usuario que realiza el post");
        }
    }

    public void validarUsuarioCreadorTopico(Long idTopico, Long idUsuario){
        Usuario usuarioBd = topicoRepository.findUsuarioByTopico(idTopico, idUsuario);
        if(usuarioBd == null){
            throw  new RuntimeException("No puede realizar modificación sobre un Tópico " +
              "no creado por usted.");
        }
    }

    public void validarTopico(Long id) {
        if (id == 0) {
            throw new RuntimeException("El post no existe");
        }
    }

    @Override
    public void validarRespuesta(String resp) {
        if (!respuestaRepository.findAll().isEmpty()) {
            Optional<Long> idRespuestaOptional = respuestaRepository.findRespuestaById(resp);

            System.out.println("El valor es:" + idRespuestaOptional.get());
            if (idRespuestaOptional.isPresent()) {
                throw new RuntimeException("La respuesta ya existe");
            }
        }
    }
}
