package es.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insertarVenta(Venta v) {
        String sql = "INSERT INTO VENTA (ID_VENTA, PRODUCTO, CANTIDAD, PRECIO) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, v.getIdVenta(), v.getProducto(), v.getCantidad(), v.getPrecio());
        System.out.println("Venta insertada correctamente: " + v);
    }


    public List<Venta> listarVentas() {
        String sql = "SELECT * FROM VENTA";
        return jdbcTemplate.query(sql, ventaRowMapper());
    }


    public List<Venta> listarPorProducto(String nombreProducto) {
        String sql = "SELECT * FROM VENTA WHERE PRODUCTO = ?";
        return jdbcTemplate.query(sql, ventaRowMapper(), nombreProducto);
    }

    private RowMapper<Venta> ventaRowMapper() {
        return (rs, rowNum) -> new Venta(
                rs.getInt("ID_VENTA"),
                rs.getString("PRODUCTO"),
                rs.getInt("CANTIDAD"),
                rs.getDouble("PRECIO")
        );
    }
}