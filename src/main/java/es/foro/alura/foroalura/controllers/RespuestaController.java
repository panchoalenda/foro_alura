package es.foro.alura.foroalura.controllers;

import es.foro.alura.foroalura.dto.*;
import es.foro.alura.foroalura.models.Respuesta;
import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.models.Usuario;
import es.foro.alura.foroalura.repository.TopicoRepository;
import es.foro.alura.foroalura.services.RespuestaService;
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
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    RespuestaService    respuestaService;

    @Autowired
    Validador           validador;

    @Autowired
    TopicoService topicoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TopicoRepository topicoRepository;

    //Listar todos los respuestas
    @GetMapping
    public ResponseEntity<Page<ListarRespuestaDTO>> listar(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable paginacion) {

        return ResponseEntity.ok(respuestaService.listarPageable(paginacion).map(ListarRespuestaDTO::new));
    }

    //Mostrar un respuesta por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Respuesta respuesta = respuestaService.buscarPorId(id).orElseThrow();

        return ResponseEntity.ok(new DatosRespuestaDTO(respuesta.getId(), respuesta.getRespondido(), respuesta.getTopico().getId(), respuesta.getUsuario().getId()));
    }

    //Agregar un respuesta
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody @Valid GuardarRespuestaDTO guardarRespuestaDTO, UriComponentsBuilder
      uriComponentsBuilder) {
        //validador.validarRespuesta(guardarRespuestaDTO.respuesta());
        //validador.validarTopico(guardarRespuestaDTO.topico().getId());

        Usuario usuarioBd = usuarioService.buscarPorId(guardarRespuestaDTO.usuario().getId()).orElseThrow();
        Topico topicoBd = topicoService.buscarPorId(guardarRespuestaDTO.topico().getId()).orElseThrow();

        Respuesta respuesta =  respuestaService.guardar(new Respuesta(guardarRespuestaDTO.respuesta(),usuarioBd, topicoBd));

        RespuestaDevueltaDTO respuestaDevueltaDTO = new RespuestaDevueltaDTO(respuesta.getId(),respuesta.getRespondido(), respuesta.getTopico().getId(), respuesta.getUsuario().getId());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuestaDevueltaDTO.id()).toUri();

        return ResponseEntity.created(url).body(respuestaDevueltaDTO);
    }

    //Actualizar un respuesta
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody @Valid ActualizarRespuestaDTO actualizarRespuestaDTO, @PathVariable Long id, UriComponentsBuilder
      uriComponentsBuilder) {
        validador.validarRespuesta(actualizarRespuestaDTO.respuesta());

        Respuesta respuesta = respuestaService.actualizar(id, new Respuesta(actualizarRespuestaDTO)).orElseThrow();

        RespuestaDevueltaDTO respuestaDevueltaDTO = new RespuestaDevueltaDTO(respuesta.getId(),respuesta.getRespondido(), respuesta.getTopico().getId(), respuesta.getUsuario().getId());

        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuestaDevueltaDTO.id()).toUri();
        return ResponseEntity.created(url).body(respuestaDevueltaDTO);
    }

    //Eliminar un respuesta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        respuestaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
