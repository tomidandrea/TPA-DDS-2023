package dominio.entidades;

import dominio.comunidades.EstadoIncidente;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Estacion;
import dominio.servicios.Agrupacion;
import dominio.servicios.Servicio;
import lombok.Getter;
import org.apache.commons.collections.ListUtils;

import javax.persistence.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Entity
public class ServicioTransporte extends Entidad {
    @Enumerated(EnumType.STRING)
    private MedioDeTransporte tipoTransporte;
    @OneToOne(cascade = CascadeType.ALL)
    private Linea lineaDeTransporteIda;
    @OneToOne(cascade = CascadeType.ALL)
    private Linea lineaDeTransporteVuelta;

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

    public Duration calcularPromedioTiempoCierre() {
        List<Duration> tiempos = estaciones().stream()
                .map(Establecimiento::obtenerListaTiemposCierre)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Optional<Duration> tiempoTotalOptional = tiempos.stream()
                .reduce(Duration::plus);

        return tiempoTotalOptional.orElse(Duration.ZERO)
                .dividedBy(tiempos.size() == 0 ? 1 : tiempos.size());
    }

    public Integer cantidadIncidentes() {

        return estaciones().stream().mapToInt(Establecimiento::cantidadDeIncidentes).sum();
    }

    public Set<Estacion> estaciones() {
        Set<Estacion> estaciones = new HashSet<>(lineaDeTransporteIda.getEstaciones());
        estaciones.addAll(lineaDeTransporteVuelta.getEstaciones());
        return estaciones;
    }

    public List<Servicio> servicios() {
        List<Servicio> servicios = new ArrayList<>();

        HashSet<Servicio> s = new HashSet<>(servicios = estaciones().stream()
                .flatMap(estacion -> estacion.getServicios().stream())
                .collect(Collectors.toList()));
        servicios = s.stream().toList();

        return servicios;
    }

}