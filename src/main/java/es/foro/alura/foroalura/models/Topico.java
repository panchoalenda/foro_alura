package es.foro.alura.foroalura.models;

import es.foro.alura.foroalura.dto.ActualizarTopicoDTO;
import es.foro.alura.foroalura.dto.TopicoRecibidoDTO;
import es.foro.alura.foroalura.dto.ListarTopicoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class    Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulos", nullable = false)
    private String titulo;

    @Column(name = "mensajes", nullable = false)
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "autores", nullable = false)
    private String autor;

    @Column(name = "cursos", nullable = false)
    private String curso;

    @JoinColumn(name = "id_usuario", nullable = false, unique = false)
    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuesta;

    public Topico(TopicoRecibidoDTO topicoRecibidoDTO, Usuario usuario) {
        this.setTitulo(topicoRecibidoDTO.titulo());
        this.setMensaje(topicoRecibidoDTO.mensaje());
        this.setAutor(topicoRecibidoDTO.autor());
        this.setCurso(topicoRecibidoDTO.curso());
        this.setUsuario(usuario);
        this.estado = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Topico(ListarTopicoDTO listarTopicoDTO) {
        this.setTitulo(listarTopicoDTO.titulo());
        this.setMensaje(listarTopicoDTO.mensaje());
        this.setAutor(listarTopicoDTO.autor());
        this.setCurso(listarTopicoDTO.curso());
    }

    public Topico(ActualizarTopicoDTO actualizarTopicoDTO) {
        this.setTitulo(actualizarTopicoDTO.titulo());
        this.setMensaje(actualizarTopicoDTO.mensaje());
        this.setAutor(actualizarTopicoDTO.autor());
        this.setEstado(actualizarTopicoDTO.estado());
        this.setCurso(actualizarTopicoDTO.curso());
    }

}
