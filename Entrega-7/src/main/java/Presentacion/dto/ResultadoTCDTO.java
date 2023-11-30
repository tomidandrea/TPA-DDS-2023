package Presentacion.dto;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultadoTCDTO {
    private EntidadDTO entidad;
    private long tiempoCierre;
    private Integer posicion = 0;

    public ResultadoTCDTO(EntidadDTO entidad, long tiempoCierre){
            this.entidad = entidad;
            this.tiempoCierre = tiempoCierre;
    }
}
