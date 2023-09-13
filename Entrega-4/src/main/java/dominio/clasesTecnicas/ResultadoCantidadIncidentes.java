package dominio.clasesTecnicas;

import dominio.entidades.Entidad;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Duration;
@Getter
@Embeddable
public class ResultadoCantidadIncidentes {
    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;
    private Integer cantidadIncidentes;

    public ResultadoCantidadIncidentes() {  }

    public ResultadoCantidadIncidentes(Entidad entidad, Integer cantidadIncidentes){
        this.entidad = entidad;
        this.cantidadIncidentes = cantidadIncidentes;
    }

    public int getCantidadIncidentes() {
        return cantidadIncidentes;
    }

    public int compararTiempo(ResultadoCantidadIncidentes otroResultado){
        return cantidadIncidentes.compareTo(otroResultado.getCantidadIncidentes());
    }
}
