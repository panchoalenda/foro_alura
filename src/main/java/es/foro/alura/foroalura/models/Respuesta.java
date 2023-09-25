package es.foro.alura.foroalura.models;

import es.foro.alura.foroalura.dto.ActualizarRespuestaDTO;
import es.foro.alura.foroalura.dto.GuardarRespuestaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "respuestas")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "respondido")
    private String respondido;

    @NotNull
    @JoinColumn(name = "id_usuario")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_topico", nullable = false)
    private Topico topico;

    public Respuesta(String respondido, Usuario usuario, Topico topico) {
        this.respondido = respondido;
        this.usuario = usuario;
        this.topico = topico;
    }

    public Respuesta(GuardarRespuestaDTO guardarRespuestaDTO) {
        setRespondido(guardarRespuestaDTO.respuesta());
        setTopico(guardarRespuestaDTO.topico());
        setUsuario(guardarRespuestaDTO.usuario());
    }

    public Respuesta(ActualizarRespuestaDTO actualizarRespuestaDTO) {
        setRespondido((actualizarRespuestaDTO.respuesta()));
    }

}
