package dominio.entidades;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Organizacion extends Entidad{
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sucursal> sucursales;

    public Organizacion() {

    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }
    public Organizacion(String nombre) {
        this.nombre = nombre;
    }

    public Organizacion(String nombre, List<Sucursal> sucursales) {
        this.nombre = nombre;
        this.sucursales = sucursales;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int compararPorPromedioTiempo(Organizacion otraOrganizacion) {
            return this.calcularPromedioTiempoCierre().compareTo(otraOrganizacion.calcularPromedioTiempoCierre());
    }

    public Duration calcularPromedioTiempoCierre(){
        List<Duration> tiempos = sucursales.stream()
            .map(Establecimiento::obtenerListaTiemposCierre)
            .flatMap(List::stream)
            .collect(Collectors.toList());

        Optional<Duration> tiempoTotalOptional = tiempos.stream()
            .reduce(Duration::plus);

        return tiempoTotalOptional.orElse(Duration.ZERO)
            .dividedBy(tiempos.size()==0?1:tiempos.size());
    }

    public Integer cantidadIncidentes(){
        return sucursales.stream().mapToInt(Establecimiento::cantidadDeIncidentes).sum();
    }

}