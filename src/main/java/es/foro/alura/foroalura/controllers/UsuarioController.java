package es.foro.alura.foroalura.controllers;

import es.foro.alura.foroalura.dto.*;
import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;
import es.foro.alura.foroalura.repository.UsuarioRepository;
import es.foro.alura.foroalura.services.UsuarioService;
import es.foro.alura.foroalura.validaciones.Validador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService    usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    Validador         validador;

    //Listar todos los usuarios
    @GetMapping
    public ResponseEntity<Page<ListarUsuarioDTO>> listar(@PageableDefault(page =0,size = 10, sort = {"id"}) Pageable paginacion) {

        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(ListarUsuarioDTO::new));
    }

    //Mostrar un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id).orElseThrow();

        return ResponseEntity.ok(new DatosUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getUsuario()));
    }

    //Agregar un usuario
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody @Valid GuardarUsuarioDTO guardarUsuarioDTO, UriComponentsBuilder
      uriComponentsBuilder) {
        System.out.println("11111llegueaaaaaaaaaaaaaaaaa");
        validador.validarUsuario(guardarUsuarioDTO.nombre(), guardarUsuarioDTO.apellido(), guardarUsuarioDTO.email(), guardarUsuarioDTO.usuario());
        System.out.println("222222222222222legueaaaaaaaaaaaaaaaaa");
        Usuario usuario = usuarioService.guardar(new Usuario(guardarUsuarioDTO));

        UsuarioDevueltoDTO usuarioDevueltoDTO = new UsuarioDevueltoDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getUsuario());

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioDevueltoDTO.id()).toUri();
        return ResponseEntity.created(url).body(usuarioDevueltoDTO);
    }


//Actualizar un usuario
@PutMapping("/{id}")
public ResponseEntity<?> actualizar(@RequestBody @Valid ActualizarUsuarioDTO actualizarUsuarioDTO, @PathVariable Long id, UriComponentsBuilder
  uriComponentsBuilder) {
    validador.validarUsuario(actualizarUsuarioDTO.nombre(), actualizarUsuarioDTO.apellido(), actualizarUsuarioDTO.email(), actualizarUsuarioDTO.usuario());
    Usuario usuario = usuarioService.actualizar(id, new Usuario(actualizarUsuarioDTO)).orElseThrow();

    UsuarioDevueltoDTO usuarioDevueltoDTO = new UsuarioDevueltoDTO(usuario.getId(),usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getUsuario());

    URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioDevueltoDTO.id()).toUri();
    return ResponseEntity.created(url).body(usuarioDevueltoDTO);
}
//Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
