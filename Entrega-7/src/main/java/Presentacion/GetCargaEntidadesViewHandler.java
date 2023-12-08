package Presentacion;

import com.opencsv.CSVReader;
import dominio.clasesTecnicas.IntegradorCSV;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetCargaEntidadesViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {

        context.render("http://localhost:4567/templates/cargaCSV.mustache");

    }

}
