package dominio.entidades;

import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.ListResourceBundle;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Entidad {


    List<Integer> promedioCierrePorEstablecimiento(List<Establecimiento> establecimientos) {
        return (List<Integer>) establecimientos.stream().flatMap(e->e.promedioCierreEnServicio().stream());
    }

  /*  Integer promedioTotalCierrePorEstablecimiento (List<Establecimiento> establecimientos) {
        return promedioCierrePorEstablecimiento(establecimientos).;
    }
*/
        
    Integer CantidadDeIncidentes() {
        return 0;
    }
}
