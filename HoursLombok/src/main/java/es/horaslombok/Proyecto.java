package es.horaslombok;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proyecto {
    @EqualsAndHashCode.Include
    private final Long id;
    private final String nombre;
}