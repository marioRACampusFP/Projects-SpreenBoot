package es.indicadores.servicios;

import es.indicadores.entidades.Indicador;
import es.indicadores.repositorios.IndicadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicadorService {

    private final IndicadorRepository repository;

    @Autowired
    public IndicadorService(IndicadorRepository repository) {
        this.repository = repository;
    }

    public List<Indicador> findAll() {
        return repository.findAll();
    }
}