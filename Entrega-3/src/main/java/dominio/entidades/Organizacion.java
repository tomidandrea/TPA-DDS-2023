package dominio.entidades;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Organizacion extends Entidad{
    String nombre;
    List<Sucursal> sucursales;

    public List<Sucursal> sucursales() {
        return sucursales;
    }
    public Organizacion(String nombre) {
        this.nombre = nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int compararPorPromedioTiempo(Organizacion otraOrganizacion) {
            return this.calcularPromedioTiempoCierre().compareTo( otraOrganizacion.calcularPromedioTiempoCierre());
    }

     Duration calcularPromedio(List<Duration> durations) {
        if (durations == null || durations.isEmpty()) {
            throw new IllegalArgumentException("La lista de duraciones no puede estar vacía.");
        }

        long totalSeconds = durations.stream()
                .mapToLong(Duration::getSeconds)
                .sum();

        long averageSeconds = totalSeconds / durations.size();

        return Duration.ofSeconds(averageSeconds);
    }

    public Duration calcularPromedioTiempoCierre(){
        Duration tiempoTotal = Duration.ZERO;
        List<Duration> tiempos = sucursales.stream().map(Establecimiento::obtenerListaTiemposCierre).
                flatMap(List::stream).collect(Collectors.toList());
        tiempos.forEach(t->tiempoTotal.plus(t));
        return tiempoTotal.dividedBy(tiempos.size());
    }

    public Integer cantidadIncidentes(){
        return sucursales.stream().mapToInt(Establecimiento::cantidadDeIncidentes).sum();
    }

}