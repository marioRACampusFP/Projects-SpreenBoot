package es.horaslombok;

import com.example.horas.model.Empleado;
import com.example.horas.model.Proyecto;
import com.example.horas.model.Tarea;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistroHorasService {

    private final Map<Long, Empleado> empleados = new HashMap<>();
    private final Map<Long, Proyecto> proyectos = new HashMap<>();
    private final List<Tarea> tareas = new ArrayList<>();

    @PostConstruct
    public void cargarDatosIniciales() {
        // 5 empleados
        for (long i = 1; i <= 5; i++) {
            empleados.put(i, new Empleado(i, "Empleado " + i));
        }

        // 10 proyectos
        for (long i = 1; i <= 10; i++) {
            proyectos.put(i, new Proyecto(i, "Proyecto " + i));
        }

        System.out.println("Datos iniciales cargados: 5 empleados y 10 proyectos.");
    }

    public Collection<Empleado> getEmpleados() { return Collections.unmodifiableCollection(empleados.values()); }
    public Collection<Proyecto> getProyectos() { return Collections.unmodifiableCollection(proyectos.values()); }
    public List<Tarea> getTareas() { return Collections.unmodifiableList(tareas); }

    public void registrarHoras(Empleado emp, Proyecto proy, int horas) {
        if (horas <= 0) throw new IllegalArgumentException("Las horas deben ser mayores que 0");
        tareas.add(new Tarea(emp, proy, horas));
    }

    public int totalHorasEmpleado(Empleado emp) {
        return tareas.stream()
                .filter(t -> t.getEmpleado().equals(emp))
                .mapToInt(Tarea::getHoras)
                .sum();
    }

    public int totalHorasProyecto(Proyecto proy) {
        return tareas.stream()
                .filter(t -> t.getProyecto().equals(proy))
                .mapToInt(Tarea::getHoras)
                .sum();
    }

    public Map<Empleado, Integer> horasPorEmpleado() {
        return empleados.values().stream()
                .collect(Collectors.toMap(
                        e -> e,
                        this::totalHorasEmpleado,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Map<Proyecto, Integer> horasPorProyecto() {
        return proyectos.values().stream()
                .collect(Collectors.toMap(
                        p -> p,
                        this::totalHorasProyecto,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}