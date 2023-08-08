import dominio.comunidades.Comunidad;
import dominio.comunidades.EstadoIncidente;
import dominio.comunidades.Incidente;
import dominio.comunidades.RepoIncidentes;
import dominio.entidades.*;
import dominio.establecimientos.Estacion;
import dominio.establecimientos.Sucursal;
import dominio.servicios.Baño;
import dominio.servicios.EscaleraMecanica;
import dominio.servicios.Servicio;
import dominio.servicios.TipoDeBaño;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestRankings {



    // Creo servicios Linea A
    Baño bañoEstacionA1 = new Baño("bañoEstacionA1", TipoDeBaño.HOMBRES);
    EscaleraMecanica escaleraEstacionA1 = new EscaleraMecanica();
    Baño bañoEstacionA2 = new Baño("bañoEstacionA2",TipoDeBaño.MUJERES);
    EscaleraMecanica escaleraEstacionA2 = new EscaleraMecanica();

    // Creo servicios Linea B
    Baño bañoEstacionB1 = new Baño("bañoEstacionB1",TipoDeBaño.HOMBRES);
    EscaleraMecanica escaleraEstacionB1 = new EscaleraMecanica();
    Baño bañoEstacionB2 = new Baño("bañoEstacionB2",TipoDeBaño.MUJERES);
    EscaleraMecanica escaleraEstacionB2 = new EscaleraMecanica();

    //Listas de servicios
    List<Servicio> serviciosEstacionA1 = new ArrayList<>();
    List<Servicio> serviciosEstacionA2 = new ArrayList<>();
    List<Servicio> serviciosEstacionB1 = new ArrayList<>();
    List<Servicio> serviciosEstacionB2 = new ArrayList<>();

    // metodo para restar tiempo al actual


    public static LocalDateTime restarDiasHorasMinutos(int dias, int horas, int minutos) {
        // Paso 1: Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Paso 2: Restar los días, horas y minutos
        LocalDateTime fechaResultante = fechaActual.minusDays(dias)
                .minusHours(horas)
                .minusMinutes(minutos);

        // Paso 3: Retornar el resultado
        return fechaResultante;
    }

    //Agrego servicios a listas
    public void configuroServiciosEstaciones() {
        serviciosEstacionA1.add(bañoEstacionA1);
        serviciosEstacionA1.add(escaleraEstacionA1);
        serviciosEstacionA2.add(bañoEstacionA2);
        serviciosEstacionA2.add(escaleraEstacionA2);
        serviciosEstacionB1.add(bañoEstacionB1);
        serviciosEstacionB1.add(escaleraEstacionB1);
        serviciosEstacionB2.add(bañoEstacionB2);
        serviciosEstacionB2.add(escaleraEstacionB2);
    }
    // =============================================================
    // Creo estaciones
    Estacion estacionA1 = new Estacion(serviciosEstacionA1);
    Estacion estacionA2 = new Estacion(serviciosEstacionA2);
    Estacion estacionB1 = new Estacion(serviciosEstacionB1);
    Estacion estacionB2 = new Estacion(serviciosEstacionB2);

    //Lista de estaciones

    List<Estacion> estacionesAIda = new ArrayList<>();
    List<Estacion> estacionesAVuelta = new ArrayList<>();
    List<Estacion> estacionesBIda = new ArrayList<>();
    List<Estacion> estacionesBVuelta = new ArrayList<>();

    //Agrego estaciones a listas

    public void configuroLineas(){
        estacionesAIda.add(estacionA1);
        estacionesAIda.add(estacionA2);
        estacionesAVuelta.add(estacionA2);
        estacionesAVuelta.add(estacionA1);
        estacionesBIda.add(estacionB1);
        estacionesBIda.add(estacionB2);
        estacionesBVuelta.add(estacionB2);
        estacionesBVuelta.add(estacionB1);
    }

    // Creo linea
    Linea lineaAIDa = new Linea("A - Ida", estacionesAIda);
    Linea lineaAVuelta = new Linea("A - Vuelta", estacionesAVuelta);
    Linea lineaBIda = new Linea("B - Ida", estacionesBIda);
    Linea lineaBVuelta = new Linea ("B - Vuelta",estacionesBVuelta);

    // =============================================================

    // Creo servicios Coto 1
    Baño bañoCoto1H = new Baño(TipoDeBaño.HOMBRES);
    Baño bañoCoto1M = new Baño(TipoDeBaño.MUJERES);

    // Creo servicios Dia 1
    Baño bañoDia1H = new Baño(TipoDeBaño.HOMBRES);
    Baño bañoDia1M = new Baño(TipoDeBaño.MUJERES);

    // Creo servicios Coto 2
    Baño bañoCoto2H = new Baño(TipoDeBaño.HOMBRES);
    Baño bañoCoto2M = new Baño(TipoDeBaño.MUJERES);

    // Creo servicios Dia 2
    Baño bañoDia2H = new Baño(TipoDeBaño.HOMBRES);
    Baño bañoDia2M = new Baño(TipoDeBaño.MUJERES);

    // Agrego servicios a listas
    List<Servicio> serviciosCoto1 = new ArrayList<>();
    List<Servicio> serviciosDia1 = new ArrayList<>();
    List<Servicio> serviciosCoto2 = new ArrayList<>();
    List<Servicio> serviciosDia2 = new ArrayList<>();

    public void configuroServiciosSucursales(){
        serviciosCoto1.add(bañoCoto1H);
        serviciosCoto1.add(bañoCoto1M);
        serviciosDia1.add(bañoDia1H);
        serviciosDia1.add(bañoDia1M);
        serviciosCoto2.add(bañoCoto2H);
        serviciosCoto2.add(bañoCoto2M);
        serviciosDia2.add(bañoDia2H);
        serviciosDia2.add(bañoDia2M);
    }

    // Creo sucursales

    Sucursal sucursalCoto1 = new Sucursal(serviciosCoto1);
    Sucursal sucursalCoto2 = new Sucursal(serviciosCoto2);
    Sucursal sucursalDia1 = new Sucursal(serviciosDia1);
    Sucursal sucursalDia2 = new Sucursal(serviciosDia2);

    // Agrego sucursales a listas

    List<Sucursal> sucursalesCoto = new ArrayList<>();
    List<Sucursal> sucursalesDia = new ArrayList<>();

    public void configuroOrganizaciones(){
        sucursalesCoto.add(sucursalCoto1);
        sucursalesCoto.add(sucursalCoto2);
        sucursalesDia.add(sucursalDia1);
        sucursalesDia.add(sucursalDia2);
    }

    // =============================================================

    //Creo entidades
    ServicioTransporte subteA = new ServicioTransporte(MedioDeTransporte.SUBTE, "subteA", lineaAIDa,lineaAVuelta);
    ServicioTransporte subteB = new ServicioTransporte(MedioDeTransporte.SUBTE, "subteb", lineaBIda,lineaBVuelta);
    Organizacion coto = new Organizacion("SUPERMECADO_COTO", sucursalesCoto);
    Organizacion dia = new Organizacion("SUPERMERCADO_DIA", sucursalesDia);


    // =============================================================

    // Parametrizo  horarios (LocalDateTime) para que funcionen los test en cualquier momento

    //guardo año actual
    int añoActual = LocalDateTime.now().getYear();
    //guardo mes actual
    int mesActual = LocalDateTime.now().getMonthValue();
    //guardo dia actual
    int diaActual = LocalDateTime.now().getDayOfMonth();

    int diaActualMenos(int dias){
        return LocalDateTime.now().minusDays(dias).getDayOfMonth();
    }

    // Incidentes del coto (mayor tiempo promedio)


    Incidente incidente1 = new Incidente(
            bañoCoto1H,
            restarDiasHorasMinutos(6, 16, 0),
            restarDiasHorasMinutos(1, 16, 30));

    Incidente incidente2 = new Incidente(bañoCoto1M,
            restarDiasHorasMinutos(6, 16, 0),
            restarDiasHorasMinutos(2, 8, 30));

    Incidente incidente3 = new Incidente(bañoCoto2H,
            restarDiasHorasMinutos(6, 8, 0),
            restarDiasHorasMinutos(3, 8, 30));
    Incidente incidente4 = new Incidente(bañoCoto2M,
            restarDiasHorasMinutos(6, 16, 0),
            restarDiasHorasMinutos(1, 8, 30));


    // Incidentes del Dia (menor tiempo promedio)
    Incidente incidente5 = new Incidente(bañoDia1H,
            restarDiasHorasMinutos(1, 8, 0),
            restarDiasHorasMinutos(0, 0, 0));
    Incidente incidente6 = new Incidente(bañoDia1M,
            restarDiasHorasMinutos(0, 8, 0),
            restarDiasHorasMinutos( 0, 0, 0));
    Incidente incidente7 = new Incidente(bañoDia2H,
            restarDiasHorasMinutos(1, 8, 0),
            restarDiasHorasMinutos(0, 1, 30));
    Incidente incidente8 = new Incidente(bañoDia2M,
            restarDiasHorasMinutos(0, 12, 30),
            restarDiasHorasMinutos(0, 8, 0));

    //==== servicios transporte

    // Incidentes del subte A - ida y vuelta (tiempo promedio intermedio)

    Incidente incidente9 = new Incidente(bañoEstacionA1,
            restarDiasHorasMinutos(3, 8, 0),
            restarDiasHorasMinutos(0, 0, 0));
    Incidente incidente10 = new Incidente(escaleraEstacionA1,
            restarDiasHorasMinutos(0, 6, 0),
            restarDiasHorasMinutos(0, 0, 0));
    Incidente incidente11 = new Incidente(bañoEstacionA2,
            restarDiasHorasMinutos(1, 16, 0),
            restarDiasHorasMinutos(0, 0, 30));
    Incidente incidente12 = new Incidente(escaleraEstacionA2,
            restarDiasHorasMinutos(1, 16, 0),
            restarDiasHorasMinutos(1, 0, 30));

    // Incidentes del subte B - ida y vuelta (tiempo promedio intermedio)

    Incidente incidente13 = new Incidente(bañoEstacionB1,
            restarDiasHorasMinutos(3, 16, 0),
            restarDiasHorasMinutos(0, 16, 30));
    Incidente incidente14 = new Incidente(escaleraEstacionB1,
            restarDiasHorasMinutos(4, 8, 0),
            restarDiasHorasMinutos(0, 16, 30));
    Incidente incidente15 = new Incidente(bañoEstacionB2,
            restarDiasHorasMinutos(1, 8, 0),
            restarDiasHorasMinutos(0, 0, 0));
    Incidente incidente16 = new Incidente(escaleraEstacionB2,
            restarDiasHorasMinutos(0, 8, 0),
            restarDiasHorasMinutos(0, 0, 30));

    // Agrego incidentes a repo

    RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();

    void agregarIncidentesARepo(){
        repoIncidentes.agregar(incidente1);
        repoIncidentes.agregar(incidente2);
        repoIncidentes.agregar(incidente3);
        repoIncidentes.agregar(incidente4);
        repoIncidentes.agregar(incidente5);
        repoIncidentes.agregar(incidente6);
        repoIncidentes.agregar(incidente7);
        repoIncidentes.agregar(incidente8);
        repoIncidentes.agregar(incidente9);
        repoIncidentes.agregar(incidente10);
        repoIncidentes.agregar(incidente11);
        repoIncidentes.agregar(incidente12);
        repoIncidentes.agregar(incidente13);
        repoIncidentes.agregar(incidente14);
        repoIncidentes.agregar(incidente15);
        repoIncidentes.agregar(incidente16);
    }

    // =============================================================
    RepoEntidades repoEntidades = RepoEntidades.getInstance();

    void agregarEntidadesARepo(){
        repoEntidades.agregar(coto);
        repoEntidades.agregar(dia);
        repoEntidades.agregar(subteA);
        repoEntidades.agregar(subteB);
    }

    @BeforeEach
    void configuracionInicial(){
        configuroServiciosEstaciones();
        configuroLineas();
        configuroServiciosSucursales();
        configuroOrganizaciones();
        agregarIncidentesARepo();
        agregarEntidadesARepo();
    }

    @Test
    public void mayorTiempoPromedioDeCierreEsCoto(){

        Assertions.assertEquals(coto, repoEntidades.obtenerRankingEntidadesConMayorTiempoDeCierre().get(0).getEntidad());
    }

    @Test
    public void menorTiempoPromedioDeCierreEsDia(){
        Assertions.assertEquals(dia, repoEntidades.obtenerRankingEntidadesConMayorTiempoDeCierre().get(3).getEntidad());
    }
}
