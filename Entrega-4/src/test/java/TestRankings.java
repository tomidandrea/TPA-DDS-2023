import dominio.comunidades.RepoIncidentes;
import dominio.entidades.*;
import dominio.rankings.RankingCantidadIncidentes;
import dominio.rankings.RankingTiempoCierre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRankings {
    private ServicioTransporte subteA;
    private ServicioTransporte subteB;
    private Organizacion coto;
    private Organizacion dia;

    private RepoIncidentes repoIncidentes = RepoIncidentes.getInstance();
    private RepoEntidades repoEntidades = RepoEntidades.getInstance();
    RankingTiempoCierre rankingTiempoCierre;
    RankingCantidadIncidentes rankingCantidadIncidentes;
    @BeforeEach
    public void init(){
        InicializadorTests inicializador = InicializadorTests.getInstance();
        this.coto = inicializador.getEntidades().getCoto();
        this.dia = inicializador.getEntidades().getDia();
        this.subteA = inicializador.getEntidades().getSubteA();
        this.subteB = inicializador.getEntidades().getSubteB();
        this.rankingTiempoCierre = new RankingTiempoCierre();
        this.rankingCantidadIncidentes = new RankingCantidadIncidentes();
    }

    @Test
    public void mayorTiempoPromedioDeCierreEsCoto(){
        rankingTiempoCierre.obtenerRankingEntidadesConMayorTiempoDeCierre();
        Assertions.assertEquals(coto, rankingTiempoCierre.getResultados().get(0).getEntidad());
    }

    @Test
    public void menorTiempoPromedioDeCierreEsDia(){
        rankingTiempoCierre.obtenerRankingEntidadesConMayorTiempoDeCierre();
        Assertions.assertEquals(dia, rankingTiempoCierre.getResultados().get(3).getEntidad());
    }

    @Test
    public void cantidadIncidentesPorEntidadUltimaSemana(){
        Assertions.assertEquals(4, subteA.cantidadIncidentes());
        Assertions.assertEquals(0, subteB.cantidadIncidentes());
        Assertions.assertEquals(1, dia.cantidadIncidentes());
        Assertions.assertEquals(2, coto.cantidadIncidentes());
    }

    @Test
    public void mayorCantidadIncidentesEsSubteA(){
        rankingCantidadIncidentes.obtenerRankingEntidadesConMasIncidentes();
        Assertions.assertEquals(subteA, rankingCantidadIncidentes.getResultados().get(0).getEntidad());
    }

    @Test
    public void segundoMayorCantidadIncidentesEsCoto(){
        rankingCantidadIncidentes.obtenerRankingEntidadesConMasIncidentes();
        Assertions.assertEquals(coto, rankingCantidadIncidentes.getResultados().get(1).getEntidad());
    }

}
