package dominio.entidades;

import dominio.comunidades.EstadoIncidente;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import org.apache.commons.collections.ListUtils;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

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

//    List<Integer> promedioCierrePorEstablecimiento(List<Establecimiento> establecimientos) {
//        // ListUtils.union(lineaDeTransporteIda.getEstaciones(), lineaDeTransporteVuelta.getEstaciones())
//        List<Estacion> estaciones = lineaDeTransporteIda.getEstaciones();
//        lineaDeTransporteVuelta.getEstaciones().forEach(e->this.agregarEstacionDistinta(e, estaciones));
//        return super.promedioCierrePorEstablecimiento(estaciones);
//    }

//    public void agregarEstacionDistinta(Estacion e, List<Estacion> estaciones){
//        if(!estaciones.contains(e)){
//            estaciones.add(e);
//        }
//    }

    public Duration calcularPromedioTiempoCierre(){
        Set<Estacion> estaciones = new HashSet<>(lineaDeTransporteIda.getEstaciones());
        estaciones.addAll(lineaDeTransporteVuelta.getEstaciones());
        Duration tiempoTotal = Duration.ZERO;
        List<Duration> tiempos = estaciones.stream().map(Establecimiento::obtenerListaTiemposCierre).
                flatMap(List::stream).collect(Collectors.toList());
        tiempos.forEach(t->tiempoTotal.plus(t));
        return tiempoTotal.dividedBy(tiempos.size());
    }

    public Integer cantidadIncidentes(){
        Set<Estacion> estaciones = new HashSet<>(lineaDeTransporteIda.getEstaciones());
        estaciones.addAll(lineaDeTransporteVuelta.getEstaciones());
        return estaciones.stream().mapToInt(Establecimiento::cantidadDeIncidentes).sum();
    }
}