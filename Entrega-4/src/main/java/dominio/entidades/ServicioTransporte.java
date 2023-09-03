package dominio.entidades;

import dominio.comunidades.EstadoIncidente;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import lombok.Getter;
import org.apache.commons.collections.ListUtils;

import javax.persistence.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Entity
public class ServicioTransporte extends Entidad{
    @Enumerated(EnumType.STRING)
    private MedioDeTransporte tipoTransporte;
    @OneToOne
    private Linea lineaDeTransporteIda;
    @OneToOne
    private  Linea lineaDeTransporteVuelta;

    public ServicioTransporte(MedioDeTransporte tipoTransporte, String nombre, Linea lineaDeTransporteIda, Linea lineaDeTransporteVuelta) {
        this.tipoTransporte = tipoTransporte;
        this.nombre = nombre;
        this.lineaDeTransporteIda = lineaDeTransporteIda;
        this.lineaDeTransporteVuelta = lineaDeTransporteVuelta;
    }

    // PAra test CSV
    public ServicioTransporte(MedioDeTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public ServicioTransporte() {

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
        //TODO: revisar si se puede cambiar el for
        for(Duration t: tiempos){
            tiempoTotal = tiempoTotal.plus(t);
            //System.out.println("Sumamos el tiempo " + t.toString() + " de un servicio Transporte");
        }
        return tiempoTotal.dividedBy(tiempos.size());
    }

    public Integer cantidadIncidentes(){
        Set<Estacion> estaciones = new HashSet<>(lineaDeTransporteIda.getEstaciones());
        estaciones.addAll(lineaDeTransporteVuelta.getEstaciones());
        return estaciones.stream().mapToInt(Establecimiento::cantidadDeIncidentes).sum();
    }
}