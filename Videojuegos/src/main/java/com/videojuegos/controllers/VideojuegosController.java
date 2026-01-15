package com.videojuegos.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;


@Controller
public class VideojuegosController {


    @GetMapping("/")
    public String inicio(Model model) {
        List<String> videojuegos = List.of(
                "The Legend of Zelda",
                "Halo Infinite",
                "Final Fantasy XVI",
                "Elden Ring"
        );
        model.addAttribute("videojuegos", videojuegos);
        return "index";
    }


    @GetMapping("/plataformas")
    public String plataformas(Model model) {
        List<String> plataformas = List.of(
                "PlayStation 5",
                "Xbox Series X",
                "Nintendo Switch",
                "PC Gaming"
        );
        model.addAttribute("plataformas", plataformas);
        return "plataformas";
    }


    @GetMapping("/generos")
    public String generos(Model model) {
        List<String> generos = List.of(
                "Acción",
                "Aventura",
                "Rol (RPG)",
                "Estrategia",
                "Simulación"
        );
        model.addAttribute("generos", generos);
        return "generos";
    }
}   