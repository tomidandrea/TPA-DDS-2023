package Presentacion.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
@Getter
public class RankingTCDTO {
    private final List<ResultadoTCDTO> resultadosDTO;
    private final LocalDate fecha;

    public RankingTCDTO(List<ResultadoTCDTO> resultados, LocalDate fecha) {
        this.resultadosDTO = resultados;
        this.fecha = fecha;
    }
}
