package Presentacion.dto;

import dominio.clasesTecnicas.ResultadoCantidadIncidentes;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
@Getter
public class RankingCIDTO {
    private final List<ResultadoCIDTO> resultadosDTO;
    private final LocalDate fecha;

    public RankingCIDTO(List<ResultadoCIDTO> resultados, LocalDate fecha) {
        this.resultadosDTO = resultados;
        this.fecha = fecha;
    }


}
