package es.foro.alura.foroalura.services;

import es.foro.alura.foroalura.dto.RespuestaDevueltaDTO;
import es.foro.alura.foroalura.models.Respuesta;
import es.foro.alura.foroalura.models.Topico;
import es.foro.alura.foroalura.repository.RespuestaRepository;
import es.foro.alura.foroalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoServiceImp implements TopicoService {
    @Autowired
    TopicoRepository    topicoRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }

    @Override
    @Transactional
    public Topico guardar(Topico topico) {
        return topicoRepository.save(topico);
    }

    @Override
    @Transactional
    public Optional<Topico> actualizar(Long id, Topico topico) {
        Topico topicoBd = null;
        Optional<Topico> o = this.buscarPorId(id);

        if (o.isPresent()) {
            topicoBd = o.get();
            topicoBd.setMensaje(topico.getMensaje());
            topicoBd.setTitulo(topico.getTitulo());
            if (topico.getEstado() != null) {
                topicoBd.setEstado(topico.getEstado());
            }
            topicoRepository.save(topicoBd);
        }
        return Optional.ofNullable(topicoBd);
    }

    @Override
    @Transactional
    public Optional<Topico> actualizarParaRespuesta(RespuestaDevueltaDTO respuestaDevueltaDTO) {

        Topico topicoBd = null;
        Respuesta respuesta = respuestaRepository.findById(respuestaDevueltaDTO.id()).orElseThrow();
        //System.out.println("respuestbd" + respuesta);
        Optional<Topico> o = this.buscarPorId(respuestaDevueltaDTO.idTopico());
        List<Respuesta> respuestaList = new ArrayList<>();
        respuestaList.add(respuesta);
        if (o.isPresent()) {
            topicoBd = o.orElseThrow();
            topicoBd.setRespuesta(respuestaList);
            topicoRepository.save(topicoBd);
        }
        return Optional.ofNullable(topicoBd);
    }

    @Override
    @Transactional
    public Optional<Topico> eliminar(Long id) {
        Optional<Topico> o = this.buscarPorId(id);
        if (o.isPresent()) {
            topicoRepository.deleteById(id);
        }
        return Optional.empty();
    }
}
