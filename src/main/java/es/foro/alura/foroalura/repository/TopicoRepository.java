package es.foro.alura.foroalura.repository;

import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByEstadoTrue(Pageable paginacion);

    @Query("""
      select t.id
       from Topico t
      where t.titulo=:tituloAGuardar
      """)
    Optional<Long> findTituloById(String tituloAGuardar);

    @Query("""
      select t.id
       from Topico t
      where t.mensaje=:mensajeAGuardar
      """)
    Optional<Long> findMensajeById(String mensajeAGuardar);
    @Query(value = "SELECT * FROM topicos t WHERE t.id = :id", nativeQuery = true)
    Optional<Topico> findById(Long id);


    @Query("""
      select t.usuario
       from Topico t
      where t.id=:idTopico AND t.usuario.id=:idUsuario
      """)
    Usuario findUsuarioByTopico(Long idTopico, Long idUsuario);
}
