package Presentacion;

import dominio.rankings.RepoRankings;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashMap;

public class RankingCIViewHandler implements Handler {

    @Override
    public void handle(Context context) throws Exception {
        var resultadosRanking = RepoRankings.getInstance().obtenerRankingCIActual().getResultados();
        var model = new HashMap<String, Object>();
        model.put("resultadosRanking", resultadosRanking);
        context.render("templates/vistasRankings/RankingCI.mustache", model);
    }
}
