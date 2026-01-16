// Categoria.java
package com.example.supermercado.model;

import lombok.Data;
import lombok.ToString;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Categoria {
    @Id
    private int idcategoria;
    private String categoria;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Producto> productos;
}