package es.foro.alura.foroalura.repository;

import es.foro.alura.foroalura.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
      SELECT u.id
      FROM Usuario u
      WHERE u.nombre = :nombre AND u.apellido = :apellido
      """)
    Optional<Long> findNombreAndApellidoById(String nombre, String apellido);

    @Query("""
      SELECT u.id
      FROM Usuario u
      WHERE u.usuario = :usuario
      """)
    Optional<Long> findUsuarioById(String usuario);

    @Query("""
      SELECT u.id
      FROM Usuario u
      WHERE u.email = :email
      """)
    Optional<Long> findEmailById(String email);
}
