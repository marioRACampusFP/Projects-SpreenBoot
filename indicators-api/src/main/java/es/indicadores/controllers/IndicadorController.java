package es.indicadores.controllers;

import es.indicadores.entidades.Indicador;
import es.indicadores.servicios.IndicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicadores")
@CrossOrigin(origins = "*")
public class IndicadorController {

    private final IndicadorService service;

    @Autowired
    public IndicadorController(IndicadorService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<Indicador> getAll() {
        return service.findAll();
    }


}