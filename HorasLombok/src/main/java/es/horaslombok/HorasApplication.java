
package es.horaslombok;

import es.horaslombok.model.Empleado;
import es.horaslombok.model.Proyecto;
import es.horaslombok.service.RegistroHorasService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class HorasLombokApplication implements CommandLineRunner {

    private final RegistroHorasService service;
    private final Scanner scanner = new Scanner(System.in);

    public HorasLombokApplication(RegistroHorasService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(HorasLombokApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== REGISTRO DE HORAS TRABAJADAS ===\n");
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
        System.out.println("¡Adiós!");
    }

    private void mostrarMenu() {
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│        MENÚ PRINCIPAL              │");
        System.out.println("├────────────────────────────────────┤");
        System.out.println("│ 1. Registrar horas                 │");
        System.out.println("│ 2. Horas por empleado              │");
        System.out.println("│ 3. Horas por proyecto              │");
        System.out.println("│ 4. Ver todos los registros         │");
        System.out.println("│ 0. Salir                           │");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("→ Opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private void procesarOpcion(int op) {
        switch (op) {
            case 1 -> registrar();
            case 2 -> horasPorEmpleado();
            case 3 -> horasPorProyecto();
            case 4 -> listarTodo();
            case 0 -> {}
            default -> System.out.println("Opción inválida\n");
        }
    }

    private void registrar() {
        System.out.println("\n--- Registrar horas ---");
        var emp = elegirEmpleado();
        var proy = elegirProyecto();
        System.out.print("Horas: ");
        int h = leerOpcion();
        if (h > 0) {
            service.registrarHoras(emp, proy, h);
            System.out.println("Registrado correctamente.\n");
        } else {
            System.out.println("Horas no válidas.\n");
        }
    }

    private Empleado elegirEmpleado() {
        System.out.println("Empleados:");
        service.getEmpleados().forEach(e -> System.out.println(e.getId() + " - " + e.getNombre()));
        System.out.print("ID empleado: ");
        long id = leerOpcion();
        return service.getEmpleados().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    private Proyecto elegirProyecto() {
        System.out.println("Proyectos:");
        service.getProyectos().forEach(p -> System.out.println(p.getId() + " - " + p.getNombre()));
        System.out.print("ID proyecto: ");
        long id = leerOpcion();
        return service.getProyectos().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    private void horasPorEmpleado() {
        System.out.println("\n--- Horas por empleado ---");
        service.horasPorEmpleado().forEach((e, h) -> System.out.println(e.getNombre() + " → " + h + " h"));
        System.out.println();
    }

    private void horasPorProyecto() {
        System.out.println("\n--- Horas por proyecto ---");
        service.horasPorProyecto().forEach((p, h) -> System.out.println(p.getNombre() + " → " + h + " h"));
        System.out.println();
    }

    private void listarTodo() {
        System.out.println("\n--- Todos los registros ---");
        if (service.getTareas().isEmpty()) {
            System.out.println("No hay registros.\n");
            return;
        }
        service.getTareas().forEach(t ->
                System.out.printf("%s → %s: %d horas%n",
                        t.getEmpleado().getNombre(),
                        t.getProyecto().getNombre(),
                        t.getHoras())
        );
        System.out.println();
    }
}