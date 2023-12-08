package dominio.clasesTecnicas;

import Utils.BDUtils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import dominio.comunidades.Miembro;
import dominio.comunidades.RepoMiembros;
import dominio.empresasYorganismos.EntidadPropietaria;
import dominio.empresasYorganismos.OrganismoDeControl;
import dominio.empresasYorganismos.RepoEntidadesPropietarias;
import dominio.empresasYorganismos.RepoOrganismosDeControl;
import dominio.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegradorCSV {
    private static IntegradorCSV instance = null;
    private EntityManager em = BDUtils.getEntityManager();
    public static IntegradorCSV getInstance(){
        if(instance == null){
            instance = new IntegradorCSV();
        }
        return instance;
    }
    // Extrae los datos del csv y devuelve una lista de Strings
    public List<String[]> extraerDesde(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader reader = new CSVReader(fileReader);
            return reader.readAll();
        } catch (CsvException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Transforma los datos extraidos del csv en objetos y los carga

    public void transformaYCarga(List<String[]> datos) {
        datos.forEach(linea -> {
            // Asumimos que ya existen los miembros responsables, servicios de transporte y organizaciones
            // También asumimos que los servicios transporte de un mismo tipo  solo pueden partenecer
            // a lo sumo a una Entidad Porpietaria y a un Organismo de Control
            // idem para las organizaciones con el mismo nombre



            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            String nombre = linea[1];
            List<ServicioTransporte> serviciosTransporte = new ArrayList<ServicioTransporte> ();
            if (linea[2].equals("ServicioTransporte")) {
                serviciosTransporte.addAll(RepoEntidades.getInstance().obtenerPorNombre(linea[3]));

            }
            List<Organizacion> organizaciones = new ArrayList<Organizacion>();
            if (linea[2].equals("Organizacion")) {
                organizaciones.addAll(RepoEntidades.getInstance().obtenerOrgPorNombre(linea[3]));
            }
            Miembro responsable = RepoMiembros.getInstance().getMiembro(Integer.parseInt(linea[4]));

            if(linea[0].equals("EntidadPropietaria")) {
                EntidadPropietaria entidadPropietaria;

                if( em.createQuery("from EntidadPropietaria where nombre = :nombre ", EntidadPropietaria.class)
                        .setParameter("nombre",nombre)
                        .getResultList()
                        .isEmpty()
                ) {
                     entidadPropietaria = new EntidadPropietaria(
                            nombre,
                            serviciosTransporte,
                            organizaciones,
                            responsable);
                    //  RepoEntidadesPropietarias.getInstance().agregar(entidadPropietaria);
                }
             else {

                 entidadPropietaria = em.createQuery("from EntidadPropietaria where nombre = :nombre ", EntidadPropietaria.class)
                         .setParameter("nombre",nombre)
                         .getResultList()
                         .get(0);

                    if (linea[2].equals("ServicioTransporte")) {
                        entidadPropietaria.agregarServicios(serviciosTransporte);

                    }

                    if (linea[2].equals("Organizacion")) {
                        entidadPropietaria.agregarOrganizaciones(organizaciones);
                    }

                }



                em.persist(entidadPropietaria);





            }else{
                if(linea[0].equals("OrganismoDeControl")) {

                    OrganismoDeControl organismoDeControl;

                    if( em.createQuery("from OrganismoDeControl where nombre = :nombre ", OrganismoDeControl.class)
                            .setParameter("nombre",nombre)
                            .getResultList()
                            .isEmpty()
                    ) {
                         organismoDeControl = new OrganismoDeControl(
                                nombre,
                                serviciosTransporte,
                                organizaciones,
                                responsable);
                    }

                    else {
                        organismoDeControl = em.createQuery("from OrganismoDeControl where nombre = :nombre ", OrganismoDeControl.class)
                                .setParameter("nombre",nombre)
                                .getResultList()
                                .get(0);

                        if (linea[2].equals("ServicioTransporte")) {
                            organismoDeControl.agregarServicios(serviciosTransporte);

                        }

                        if (linea[2].equals("Organizacion")) {
                            organismoDeControl.agregarOrganizaciones(organizaciones);
                        }
                    // RepoOrganismosDeControl.getInstance().agregar(organismoDeControl);
                    }

                em.persist(organismoDeControl);

                }
            }
            transaction.commit();




        });
        em.close();
    }

    private MedioDeTransporte convertirTransporte(String transporte) {
        switch (transporte) {
            case "SUBTE":
                return MedioDeTransporte.SUBTE;
            case "TREN":
                return MedioDeTransporte.TREN;
            case "COLECTIVO":
                return MedioDeTransporte.COLECTIVO;
            case "ECOBICI":
                return MedioDeTransporte.ECOBICI;
            default:
                throw new RuntimeException("Transporte no valido");
        }
    }
}

