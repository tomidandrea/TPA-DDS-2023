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


public class PostCargaEntidadesViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {

        UploadedFile uploadedFile = context.uploadedFile("FileCSV");
     //   UploadedFile uploadedFile = context.uploadedFiles("FileCSV").get(0);
        System.out.println("entré al handle de csv");

        if (uploadedFile != null) {

            System.out.println("llegó el archivo");
            // Procesar el archivo cargado (en este caso, imprimir su contenido)
            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uploadedFile.content()));

                CSVReader reader = new CSVReader(bufferedReader);
                IntegradorCSV integradorCSV = IntegradorCSV.getInstance();
                List <String[]> archivoCSV = reader.readAll();
                integradorCSV.transformaYCarga(archivoCSV);

            } catch (IOException e) {
                System.out.println("error catch");
                e.printStackTrace();
            }
        }
        else {

            System.out.println("el archivo es null");

        }

        context.status(201);
        context.redirect("http://localhost:4567/cargaCSV");

    }
/*
    private static String obtenerContenidoArchivo(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

*/
}
