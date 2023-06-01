package ddsGrupo1.integracionCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ddsGrupo1.Miembro;
import ddsGrupo1.RepoMiembros;
import ddsGrupo1.empresasYorganismos.EntidadPropietaria;
import ddsGrupo1.empresasYorganismos.OrganismoDeControl;
import ddsGrupo1.empresasYorganismos.RepoEntidadesPropietarias;
import ddsGrupo1.empresasYorganismos.RepoOrganismosDeControl;
import ddsGrupo1.entidades.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegradorCSV {
    private static IntegradorCSV instance = null;

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
            String nombre = linea[1];
            ArrayList<ServicioTransporte> serviciosTransporte = new ArrayList<ServicioTransporte>();
            if (linea[2].equals("ServicioTransporte")) {
                serviciosTransporte.add(RepoServiciosDeTransporte.getInstance().obtenerPorTipo(convertirTransporte(linea[2])));
            }
            ArrayList<Organizacion> organizaciones = new ArrayList<Organizacion>();
            if (linea[3].equals("Organizacion")) {
                organizaciones.add(RepoOrganizaciones.getInstance().obtenerPorNombre(linea[3]));
            }
            Miembro responsable = RepoMiembros.getInstance().getMiembro(Integer.parseInt(linea[4]));

            if(linea[0].equals("EntidadPropietaria")) {

                EntidadPropietaria entidadPropietaria = new EntidadPropietaria(
                        nombre,
                        serviciosTransporte,
                        organizaciones,
                        responsable);

                RepoEntidadesPropietarias.getInstance().agregar(entidadPropietaria);

            }else if(linea[0].equals("OrganismoDeControl")) {
                OrganismoDeControl organismoDeControl = new OrganismoDeControl(
                        nombre,
                        serviciosTransporte,
                        organizaciones,
                        responsable);

                RepoOrganismosDeControl.getInstance().agregar(organismoDeControl);
            }
        });
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

