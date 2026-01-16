package es.ventas;

public class Venta {
    private int idVenta;
    private String producto;
    private int cantidad;
    private double precio;

    public Venta() {}

    public Venta(int idVenta, String producto, int cantidad, double precio) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getImporte() {
        return cantidad * precio;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Producto: %-18s | Cantidad: %2d | Precio: %6.2f € | Importe: %7.2f €",
                idVenta, producto, cantidad, precio, getImporte());
    }
}