import dominio.comunidades.Miembro;
import dominio.comunidades.RepoMiembros;
import dominio.empresasYorganismos.RepoEntidadesPropietarias;
import dominio.entidades.*;
import dominio.clasesTecnicas.IntegradorCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCSV {

    // Inicializo servicios de transporte
    ServicioTransporte subte = new ServicioTransporte(MedioDeTransporte.SUBTE);
    ServicioTransporte tren = new ServicioTransporte(MedioDeTransporte.TREN);
    ServicioTransporte colectivo = new ServicioTransporte(MedioDeTransporte.COLECTIVO);
    ServicioTransporte ecobici = new ServicioTransporte(MedioDeTransporte.ECOBICI);

    void inicializarServiciosDeTransporte() {
        RepoEntidades.getInstance().agregar(subte);
        RepoEntidades.getInstance().agregar(tren);
        RepoEntidades.getInstance().agregar(colectivo);
        RepoEntidades.getInstance().agregar(ecobici);
    }

    // Inicializo organizaciones
    Organizacion organizacion1 = new Organizacion("SUPERMECADO_COTO");

    void inicializarOrganizaciones() {
        RepoEntidades.getInstance().agregar(organizacion1);
    }

    // Inicializo miembros
    Miembro agus = new Miembro("Agustin", "Sanjuan", "agus@gmail.com", "1234", "ChefCurry30!", 1);
    Miembro juan = new Miembro("Juan", "Perez", "juan@gmai.com", "1234", "ChefCurry30!", 2);
    Miembro maria = new Miembro("Maria", "Gonzalez", "maria@gmail.com", "1234", "ChefCurry30!", 3);
    Miembro pedro = new Miembro("Pedro", "Gomez", "pepe@gmail.com", "1234", "ChefCurry30!", 4);
    Miembro lucas = new Miembro("Lucas", "Rodriguez", "luquitaRodrigue@gmail.com", "1234", "ChefCurry30!", 5);

    RepoMiembros repoMiembros = RepoMiembros.getInstance();
    void inicializarMiembros() {

        repoMiembros.agregar(agus);
        repoMiembros.agregar(juan);
        repoMiembros.agregar(maria);
        repoMiembros.agregar(pedro);
        repoMiembros.agregar(lucas);
    }

    @BeforeEach
    void setUp() {
        inicializarServiciosDeTransporte();
        inicializarOrganizaciones();
        inicializarMiembros();
    }

    @Test
    public void seExtraeListaDelcsv() {
        List<String[]> datos = IntegradorCSV.getInstance().extraerDesde("resources/entidades-organismos.csv");
        Assertions.assertEquals(8, datos.size());
    }

    @Test
    public void seAgreganEntidadesPropietarias() {
        List<String[]> datos = IntegradorCSV.getInstance().extraerDesde("resources/entidades-organismos.csv");
        IntegradorCSV.getInstance().transformaYCarga(datos);
        Assertions.assertEquals(4, RepoEntidadesPropietarias.getInstance().getEntidadesPropietarias().size());
    }

}
