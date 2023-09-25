package es.foro.alura.foroalura.services;

import es.foro.alura.foroalura.models.Respuesta;
import es.foro.alura.foroalura.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImp implements RespuestaService {
    @Autowired
    RespuestaRepository respuestaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> listar() {
        return respuestaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Respuesta> listarPageable(Pageable pageable) {
        return respuestaRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Respuesta> buscarPorId(Long id) {
        return respuestaRepository.findById(id);
    }

    @Override
    @Transactional
    public Respuesta guardar(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    @Override
    @Transactional
    public Optional<Respuesta> actualizar(Long id, Respuesta respuesta) {
        Respuesta respuestaBd = null;
        Optional<Respuesta> o = this.buscarPorId(id);

        if (o.isPresent()) {
            respuestaBd = o.get();
            respuestaBd.setRespondido(respuesta.getRespondido());
            respuestaBd.setTopico(respuesta.getTopico());
            respuestaBd.setUsuario(respuesta.getUsuario());
            respuestaRepository.save(respuestaBd);
        }
        return Optional.ofNullable(respuestaBd);
    }

    @Override
    @Transactional
    public Optional<Respuesta> eliminar(Long id) {
        Optional<Respuesta> o = this.buscarPorId(id);
        if (o.isPresent()) {
            respuestaRepository.deleteById(id);
        }
        return Optional.empty();
    }
}
