package Presentacion.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntidadDTO {

    private int id;
    private String nombre;

    public EntidadDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
