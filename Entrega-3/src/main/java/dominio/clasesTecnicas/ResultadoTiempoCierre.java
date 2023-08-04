package dominio.clasesTecnicas;

import dominio.entidades.Entidad;
import lombok.Getter;

import java.time.Duration;

public class ResultadoTiempoCierre {
    @Getter
    private Entidad entidad;
    private Duration tiempoDeCierre;

    public ResultadoTiempoCierre(Entidad entidad, Duration tiempoDeCierre){
        this.entidad = entidad;
        this.tiempoDeCierre = tiempoDeCierre;
    }

    public Duration getTiempoDeCierre() {
        return tiempoDeCierre;
    }

    public int compararTiempo(ResultadoTiempoCierre otroResultado){
        return tiempoDeCierre.compareTo(otroResultado.getTiempoDeCierre());
    }
}
