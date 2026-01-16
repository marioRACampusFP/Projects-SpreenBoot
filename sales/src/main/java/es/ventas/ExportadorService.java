package es.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ExportadorService {

    @Autowired
    private VentaService ventaService;

    public void exportar() {
        List<Venta> ventas = ventaService.listarVentas();
        double totalRecaudacion = 0;

        try (FileWriter fw = new FileWriter("Ventas.txt")) {
            fw.write("                   LISTADO DE VENTAS                      \n");
            fw.write("                                                          \n");

            for (Venta v : ventas) {
                double importe = v.getImporte();
                totalRecaudacion += importe;
                fw.write(v.toString() + "\n");
            }
            fw.write(String.format("Recaudacion total:                          %8.2f €\n", totalRecaudacion));

            System.out.println("Exportación completada → Ventas.txt creado con éxito");

        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}