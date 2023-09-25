package es.foro.alura.foroalura.repository;

import es.foro.alura.foroalura.models.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("""
      select r.id
       from Respuesta r
      where r.respondido=:respuestaAGuardar
      """)
    Optional<Long> findRespuestaById(String respuestaAGuardar);
}
