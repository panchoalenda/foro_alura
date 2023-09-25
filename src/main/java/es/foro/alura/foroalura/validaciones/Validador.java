package es.foro.alura.foroalura.validaciones;

public interface Validador {

    void validarTituloMensaje(String titulo, String mensaje);

    void validarUsuario(String nombre, String apellido, String email, String usuario);
    void validarUsuario(Long id);

    void validarRespuesta(String respuesta);
    public void validarTopico(Long id);

    void validarUsuarioCreadorTopico(Long idTopico, Long idUsuario);
}
