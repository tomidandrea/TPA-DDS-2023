package ddsGrupo1.integracionCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ddsGrupo1.Miembro;
import ddsGrupo1.RepoMiembros;
import ddsGrupo1.empresasYorganismos.EntidadPropietaria;
import ddsGrupo1.empresasYorganismos.OrganismoDeControl;
import ddsGrupo1.entidades.Organizacion;
import ddsGrupo1.entidades.ServicioTransporte;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {

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

    // Transforma los datos extraidos en una lista de EntidadPropietaria

    public List<EntidadPropietaria> transformaYCarga(List<String[]> datos) {
        datos.forEach(linea -> {
            //verificar si existe entidad

            //verificar que exista servicio u organizacion

            //verificar que exista miembro

            if(linea[0].equals("EntidadPropietaria")) {
                ArrayList<Organizacion> organizaciones = new ArrayList<Organizacion>();
                organizaciones.add(new Organizacion(linea[0]));
                ArrayList<ServicioTransporte> serviciosTransporte = new ArrayList<ServicioTransporte>();

                Miembro responsable = RepoMiembros.getInstance().getMiembro(Integer.parseInt(linea[1]));
                EntidadPropietaria entidadPropietaria = new EntidadPropietaria(
                       organizaciones,
                        serviciosTransporte,
                        responsable);
            }else if(linea[0].equals("OrganismoDeControl")) {
                OrganismoDeControl organismoDeControl = new OrganismoDeControl();
                //todo: seguir esto
            }
        });
    }
}

