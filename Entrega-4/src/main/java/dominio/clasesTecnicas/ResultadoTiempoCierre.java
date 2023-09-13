package dominio.clasesTecnicas;

import dominio.entidades.Entidad;
import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
@Embeddable
@Getter
public class ResultadoTiempoCierre {
    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;
    private Duration tiempoDeCierre;

    public ResultadoTiempoCierre() { }

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
