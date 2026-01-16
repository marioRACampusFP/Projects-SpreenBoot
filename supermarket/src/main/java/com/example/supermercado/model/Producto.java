// Producto.java
package com.example.supermercado.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Producto {
    @Id
    private int idproducto;
    private String nombre;
    private String medida;
    private int precio;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;
}