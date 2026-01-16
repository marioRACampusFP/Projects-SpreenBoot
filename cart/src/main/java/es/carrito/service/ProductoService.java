package es.carrito.service;

import es.carrito.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Producto> todos() {
        return jdbc.query(
                "SELECT idproducto, nombre, precio FROM producto ORDER BY nombre",
                (rs, row) -> new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3))
        );
    }
}