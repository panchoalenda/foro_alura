package es.foro.alura.foroalura.controllers;

import es.foro.alura.foroalura.dto.*;
import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;
import es.foro.alura.foroalura.repository.TopicoRepository;
import es.foro.alura.foroalura.services.TopicoService;
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
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    TopicoService    topicoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    Validador validador;

    //Listar todos los tópicos
    @GetMapping
    public ResponseEntity<Page<ListarTopicoDTO>> listar(@PageableDefault(page =0,size = 10, sort = {"id"}) Pageable paginacion) {

        return ResponseEntity.ok(topicoRepository.findAllByEstadoTrue(paginacion).map(ListarTopicoDTO::new));
    }

    //Mostrar un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        System.out.println("id: " + topicoRepository.existsById(id));
        System.out.println("Topico: " + topicoRepository.findById(id).get());

        Topico topico = topicoService.buscarPorId(id).orElseThrow();

        return ResponseEntity.ok(new DatosTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
          topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso()));
    }

    //Agregar un tópico
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody @Valid TopicoRecibidoDTO topicoRecibidoDTO, UriComponentsBuilder
      uriComponentsBuilder) {
        validador.validarTituloMensaje(topicoRecibidoDTO.titulo(), topicoRecibidoDTO.mensaje());
        validador.validarUsuario(topicoRecibidoDTO.usuario());

        Usuario usuariobd = usuarioService.buscarPorId(topicoRecibidoDTO.usuario()).orElseThrow();

        Topico topico = topicoService.guardar(new Topico(topicoRecibidoDTO, usuariobd));

        TopicoRespuestaDTO topicoRespuestaDTO = new TopicoRespuestaDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRespuestaDTO.id()).toUri();
        return ResponseEntity.created(url).body(topicoRespuestaDTO);
    }


//Actualizar un tópico
@PutMapping("/{id}")
public ResponseEntity<?> actualizar(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO, @PathVariable Long id, UriComponentsBuilder
  uriComponentsBuilder) {
    validador.validarTituloMensaje(actualizarTopicoDTO.titulo(), actualizarTopicoDTO.mensaje());
    validador.validarUsuarioCreadorTopico(id,actualizarTopicoDTO.usuarioCreador());
    Topico topico = topicoService.actualizar(id, new Topico(actualizarTopicoDTO)).orElseThrow();

    TopicoRespuestaDTO topicoRespuestaDTO = new TopicoRespuestaDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());

    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRespuestaDTO.id()).toUri();
    return ResponseEntity.created(url).body(topicoRespuestaDTO);
}
//Eliminar un tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
