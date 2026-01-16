// SupermercadoApplication.java
package com.example.supermercado;

import com.example.supermercado.model.Categoria;
import com.example.supermercado.model.Producto;
import com.example.supermercado.repository.CategoriaRepository;
import com.example.supermercado.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SupermercadoApplication implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SupermercadoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ SUPERMERCADO ===");
            System.out.println("1. Mostrar todos los productos");
            System.out.println("2. Productos por categoría (listado agrupado)");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Productos de una categoría (seleccionar por ID)");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> mostrarTodosLosProductos();
                case 2 -> productosAgrupadosPorCategoria();
                case 3 -> buscarProductoPorNombre(sc);
                case 4 -> productosDeUnaCategoria(sc);
                case 5 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);

        sc.close();
    }

    private void mostrarTodosLosProductos() {
        System.out.println("\n--- TODOS LOS PRODUCTOS ---");
        productoRepository.findAll().forEach(p ->
                System.out.printf("ID: %d | %s | %s | Precio: %d | Stock: %d | Cat: %s%n",
                        p.getIdproducto(), p.getNombre(), p.getMedida(),
                        p.getPrecio(), p.getStock(),
                        p.getCategoria().getCategoria()));
    }

    private void productosAgrupadosPorCategoria() {
        System.out.println("\n--- PRODUCTOS AGRUPADOS POR CATEGORÍA ---");
        List<Categoria> categorias = categoriaRepository.findAll();
        for (Categoria c : categorias) {
            System.out.println("\nCategoría: " + c.getCategoria());
            System.out.println("---------------------------");
            c.getProductos().forEach(p ->
                    System.out.printf("  %s - %s - %d € - Stock: %d%n",
                            p.getNombre(), p.getMedida(), p.getPrecio(), p.getStock()));
        }
    }

    private void buscarProductoPorNombre(Scanner sc) {
        System.out.print("\nIntroduce parte del nombre del producto: ");
        String texto = sc.nextLine();
        List<Producto> resultados = productoRepository
                .findByNombreContainingIgnoreCase(texto);

        System.out.printf("\n--- RESULTADOS PARA '%s' (%d encontrados) ---%n", texto, resultados.size());
        resultados.forEach(p ->
                System.out.printf("ID: %d | %s | %s | %d € | Cat: %s%n",
                        p.getIdproducto(), p.getNombre(), p.getMedida(),
                        p.getPrecio(), p.getCategoria().getCategoria()));
    }

    private void productosDeUnaCategoria(Scanner sc) {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        List<Categoria> categorias = categoriaRepository.findAll();
        categorias.forEach(c ->
                System.out.printf("ID: %d | %s%n", c.getIdcategoria(), c.getCategoria()));

        System.out.print("\nIntroduce el ID de la categoría: ");
        int id = sc.nextInt();
        sc.nextLine();

        List<Producto> productos = productoRepository.findByCategoriaIdcategoria(id);

        Categoria cat = categoriaRepository.findById(id).orElse(null);
        String nombreCat = (cat != null) ? cat.getCategoria() : "Desconocida";

        System.out.printf("\n--- PRODUCTOS DE LA CATEGORÍA: %s (%d productos) ---%n",
                nombreCat, productos.size());

        if (productos.isEmpty()) {
            System.out.println("No hay productos en esta categoría.");
        } else {
            productos.forEach(p ->
                    System.out.printf("ID: %d | %s | %s | %d € | Stock: %d%n",
                            p.getIdproducto(), p.getNombre(), p.getMedida(),
                            p.getPrecio(), p.getStock()));
        }
    }
}