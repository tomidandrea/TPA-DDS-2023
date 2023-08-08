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
    private ServicioTransporte subteA;
    private ServicioTransporte subteB;
    private Organizacion coto;
    private Organizacion dia;

    private RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
    private RepoEntidades repoEntidades = RepoEntidades.getInstance();
    @BeforeEach
    public void init(){
        InicializadorTests inicializador = InicializadorTests.getInstance();
        this.coto = inicializador.getEntidades().getCoto();
        this.dia = inicializador.getEntidades().getDia();
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
