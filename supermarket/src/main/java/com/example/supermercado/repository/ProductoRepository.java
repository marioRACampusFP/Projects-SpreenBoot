// ProductoRepository.java
package com.example.supermercado.repository;

import com.example.supermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // 3. Buscar producto por parte del nombre
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', :texto, '%'))")
    List<Producto> findByNombreContainingIgnoreCase(String texto);

    // 2. Productos por categoría (usaremos findAll + filtro en Java o por ID)
    List<Producto> findByCategoriaIdcategoria(int idcategoria);
}