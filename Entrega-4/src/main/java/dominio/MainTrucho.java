package dominio;

import dominio.Localizacion.ListadoDeProvincias;
import dominio.Localizacion.Provincia;
import dominio.Localizacion.ServicioGeoRef;
import dominio.comunidades.RepoComunidades;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainTrucho {
    public static void main(String[] args) throws IOException {
        ServicioGeoRef servicioGeoRef = ServicioGeoRef.getInstance();

        System.out.println("Provincias:");
        ListadoDeProvincias listadoDeProvincias = servicioGeoRef.listadoDeProvincias();

        //mostramos las provincias
        for (Provincia unaProvincia : listadoDeProvincias.provincias) {

            System.out.println(unaProvincia.getId() + ") " + unaProvincia.nombre + "| Lat: " + unaProvincia.centroide.lat + "| Long: " + unaProvincia.centroide.lon);
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Programa la tarea para que se ejecute cada 5 segundos
        scheduler.scheduleAtFixedRate(new Notificar(), 0, 1, TimeUnit.HOURS);

    }

    static class Notificar implements Runnable {
        @Override
        public void run() {
            System.out.println("Enviando notificaciones...");
            RepoComunidades.getInstance().getComunidades().forEach(comunidad -> comunidad.notificarIncidentes());
        }
    }
}
