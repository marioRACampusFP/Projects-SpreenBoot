package es.carrito.model;

public class Producto {
    private int idproducto;
    private String nombre;
    private double precio;

    public Producto(int idproducto, String nombre, double precio) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdproducto() { return idproducto; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
}