package Presentacion.dto;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultadoTCDTO {
    private EntidadDTO entidad;
    private Duration tiempoCierre;

    public ResultadoTCDTO(EntidadDTO entidad, Duration tiempoCierre){
            this.entidad = entidad;
            this.tiempoCierre = tiempoCierre;
    }
}
