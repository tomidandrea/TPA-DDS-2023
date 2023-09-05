import Utils.BDUtils;
import dominio.Localizacion.Centroide;
import dominio.Localizacion.Localizacion;
import dominio.Localizacion.Municipio;
import dominio.Localizacion.Provincia;
import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.comunidades.Incidente;
import dominio.comunidades.Miembro;
import dominio.comunidades.Notificador;
import dominio.comunidades.TipoMiembro;
import dominio.servicios.Banio;
import dominio.servicios.EscaleraMecanica;
import dominio.servicios.TipoDeBanio;
import dominio.servicios.TramoMedioElevacion;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);
    Centroide centroide = new Centroide(12.0,34.0);
    Provincia provincia = new Provincia("Buenos Aires", centroide);
    em.persist(provincia);
    Banio banio = new Banio("Baño primer piso", TipoDeBanio.HOMBRES);
    em.persist(banio);
    em.persist(new EscaleraMecanica(provincia, "Escalera bajada desde calle", TramoMedioElevacion.CALLE_A_ACCESO));
    LocalDateTime horarioApertura = LocalDateTime.now();
    Incidente incidente = new Incidente(banio, horarioApertura, "Canilla pierde agua");
    em.persist(incidente);

    List<LocalTime> horarios = new ArrayList<>();
    horarios.add(LocalTime.now());
    horarios.add(LocalTime.now().plusHours(5));
    Notificador notificador = new Notificador(horarios);
    //em.persist(notificador);

    Miembro miembro = new Miembro("Pepe", "Argento", "pepe@argento", provincia,
        TipoMiembro.AFECTADO, new MedioCorreo(), "1234", notificador,
        "pepeArg", "23campeonDelMundo#3");
    em.persist(miembro);

    BDUtils.commit(em);
    em.close();
  }

}