package dominio.entidades;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Organizacion extends Entidad{
    private List<Sucursal> sucursales;

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
        //TODO: revisar si se puede cambiar el for
        for(Duration t: tiempos){
            tiempoTotal = tiempoTotal.plus(t);
            //System.out.println("Sumamos el tiempo " + t.toString() + " de una organizacion");
        }
        return tiempoTotal.dividedBy(tiempos.size());
    }

    public Integer cantidadIncidentes(){
        return sucursales.stream().mapToInt(Establecimiento::cantidadDeIncidentes).sum();
    }

}