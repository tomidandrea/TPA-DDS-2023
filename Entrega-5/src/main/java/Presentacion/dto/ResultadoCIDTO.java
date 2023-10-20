package Presentacion.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultadoCIDTO {
    private EntidadDTO entidad;
    private Integer cantidadIncidentes;

    public ResultadoCIDTO(EntidadDTO entidad, Integer cantidadIncidentes){
        this.entidad = entidad;
        this.cantidadIncidentes = cantidadIncidentes;
    }
}
