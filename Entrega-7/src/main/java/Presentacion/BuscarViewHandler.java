package Presentacion;

import Presentacion.dto.UsuarioDTO;
import dominio.comunidades.TipoMiembro;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.List;


public class BuscarViewHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        String busqueda = ctx.formParam("searchInput");
        String filtro = ctx.formParam("filterOptions");

//        List<UsuarioDTO> listaFiltrada = elementos.stream()
//                .filter(item -> filtro.equals("Todos") || filtro.equals(item.getTipo()))
//                .filter(item -> item.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
//                .toList();
//
//        StringBuilder result = new StringBuilder("<ul>");
//        listaFiltrada.forEach(item -> result.append("<li>").append(item.getNombre()).append(" - ").append(item.getTipo()).append("</li>"));
//        result.append("</ul>");

//        ctx.result(result.toString());
    }

}
