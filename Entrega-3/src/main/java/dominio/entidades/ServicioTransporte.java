package dominio.entidades;

import dominio.comunidades.EstadoIncidente;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import org.apache.commons.collections.ListUtils;

import java.time.Duration;
import java.util.List;

public class ServicioTransporte extends Entidad{
    MedioDeTransporte tipoTransporte;
    Linea lineaDeTransporteIda;
    Linea lineaDeTransporteVuelta;

    public ServicioTransporte(MedioDeTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public void setTipoTransporte(MedioDeTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    List<Integer> promedioCierrePorEstablecimiento(List<Establecimiento> establecimientos) {
        // ListUtils.union(lineaDeTransporteIda.getEstaciones(), lineaDeTransporteVuelta.getEstaciones())
        List<Estacion> estaciones = lineaDeTransporteIda.getEstaciones();
        lineaDeTransporteVuelta.getEstaciones().forEach(e->this.agregarEstacionDistinta(e, estaciones));
        return super.promedioCierrePorEstablecimiento(estaciones);
    }

    public void agregarEstacionDistinta(Estacion e, List<Estacion> estaciones){
        if(!estaciones.contains(e)){
            estaciones.add(e);
        }
    }
}