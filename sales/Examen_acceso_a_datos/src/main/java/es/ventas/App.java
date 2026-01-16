package es.ventas;

import es.ventas.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        VentaService ventaService = context.getBean(VentaService.class);
        ExportadorService exportador = context.getBean(ExportadorService.class);

        System.out.println("1. Mostrando todas las ventas");
        ventaService.listarVentas().forEach(System.out::println);

        System.out.println("\n2. Insertando nueva venta");
        Venta nueva = new Venta(17, "Zapatillas baloncesto", 2, 60.99);
        ventaService.insertarVenta(nueva);

        System.out.println("\n3. Ventas del producto 'Zapatillas negras'");
        ventaService.listarPorProducto("Zapatillas negras").forEach(System.out::println);

        System.out.println("\n4. Exportando al fichero...");
        exportador.exportar();

        ((AnnotationConfigApplicationContext) context).close();
    }
}