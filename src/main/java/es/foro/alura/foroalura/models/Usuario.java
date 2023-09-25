package es.foro.alura.foroalura.models;

import es.foro.alura.foroalura.dto.ActualizarUsuarioDTO;
import es.foro.alura.foroalura.dto.GuardarUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "usuario")
    private String usuario;


    public Usuario(GuardarUsuarioDTO guardarUsuarioDTO) {
        this.setNombre(guardarUsuarioDTO.nombre());
        this.setApellido(guardarUsuarioDTO.apellido());
        this.setEmail(guardarUsuarioDTO.email());
        this.setUsuario(guardarUsuarioDTO.usuario());
    }

    public Usuario(ActualizarUsuarioDTO actualizarUsuarioDTO) {
        this.setNombre(actualizarUsuarioDTO.nombre());
        this.setApellido(actualizarUsuarioDTO.apellido());
        this.setEmail(actualizarUsuarioDTO.email());
        this.setUsuario(actualizarUsuarioDTO.usuario());
    }
}
