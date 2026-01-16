package es.horaslombok;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Tarea {
    private final Empleado empleado;
    private final Proyecto proyecto;
    private final int horas;
}