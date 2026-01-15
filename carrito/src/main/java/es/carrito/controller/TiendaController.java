package es.carrito.controller;

import es.carrito.application.CarritoGlobal;
import es.carrito.service.ProductoService;
import es.carrito.session.CarritoSesion;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TiendaController {

    @Autowired
    private CarritoSesion carritoSesion;

    @Autowired
    private CarritoGlobal carritoGlobal;

    @Autowired
    private ProductoService productoService;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("productos", productoService.todos());
        mv.addObject("carritoSesion", carritoSesion.getItems());
        mv.addObject("carritoGlobal", carritoGlobal.getItems());
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            productoService.todos().stream()
                    .filter(p -> p.getIdproducto() == id)
                    .findFirst()
                    .ifPresent(p -> {
                        String texto = p.getNombre() + " - " + p.getPrecio() + "€";
                        carritoSesion.add(texto);      // ← carrito del usuario
                        carritoGlobal.add(texto);      // ← carrito de todos
                    });
        }
        return "redirect:/";
    }
}