package dominio.entidades;

import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.ListResourceBundle;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Entidad {
    public abstract Duration calcularPromedioTiempoCierre();
    public abstract Integer cantidadIncidentes();

    public List<Duration> promediosDeCierrePorCadaEstablecimiento(List<Establecimiento> establecimientos) {
        return (List<Duration>) establecimientos.stream().flatMap(e->e.obtenerListaTiemposCierre().stream());
    }

  /*  Integer promedioTotalCierrePorEstablecimiento (List<Establecimiento> establecimientos) {
        return promedioCierrePorEstablecimiento(establecimientos).;
    }
*/
      /*public Duration calcularPromedioTiempoCierre(List<Establecimiento> establecimientos){
          Duration tiempoTotal = Duration.ZERO;
          List<Duration> tiempos = establecimientos.stream().map(Establecimiento::obtenerListaTiemposCierre).
                  flatMap(List::stream).collect(Collectors.toList());
          tiempos.forEach(t->tiempoTotal.plus(t));
          return tiempoTotal.dividedBy(tiempos.size());
      }*/
        
    Integer CantidadDeIncidentes() {
        return 0;
    }
}
