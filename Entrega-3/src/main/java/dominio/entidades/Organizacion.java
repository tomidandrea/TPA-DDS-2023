package dominio.entidades;
import dominio.establecimientos.Establecimiento;
import dominio.establecimientos.Sucursal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Organizacion extends Entidad{
    String nombre;
    List<Sucursal> sucursales;

    public Organizacion(String nombre) {
        this.nombre = nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int compararPorPromedioTiempo(Organizacion otraOrganizacion) {
        if (this.promedioCierrePorEstablecimiento() > otraOrganizacion.promedioCierrePorEstablecimiento()){
            return 1;
        }
        if (this.promedioCierrePorEstablecimiento() < otraOrganizacion.promedioCierrePorEstablecimiento()){
            return -1;
        }
        if (this.promedioCierrePorEstablecimiento() == otraOrganizacion.promedioCierrePorEstablecimiento()){
            return 0;
        }

    }
    


    List<Integer> promedioCierrePorEstablecimiento(List<Establecimiento> establecimientos) {
        return super.promedioCierrePorEstablecimiento(sucursales);
    }
}