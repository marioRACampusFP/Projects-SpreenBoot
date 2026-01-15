package es.horaslombok;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empleado {
    @EqualsAndHashCode.Include
    private final Long id;
    private final String nombre;
}