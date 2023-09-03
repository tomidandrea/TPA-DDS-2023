import Utils.BDUtils;
import dominio.Localizacion.Centroide;
import dominio.Localizacion.Localizacion;
import dominio.Localizacion.Municipio;
import dominio.Localizacion.Provincia;
import dominio.servicios.Banio;
import dominio.servicios.EscaleraMecanica;
import dominio.servicios.TipoDeBanio;
import dominio.servicios.TramoMedioElevacion;

import javax.persistence.EntityManager;

public class Main {

  public static void main(String[] args) {

    EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);
    Centroide centroide = new Centroide(12.0,34.0);
    Provincia provincia = new Provincia("Buenos Aires", centroide);
    em.persist(provincia);
    em.persist(new Banio("Baño primer piso", TipoDeBanio.HOMBRES));
    em.persist(new EscaleraMecanica(provincia, "Escalera bajada desde calle", TramoMedioElevacion.CALLE_A_ACCESO));

    BDUtils.commit(em);
    em.close();
  }

}