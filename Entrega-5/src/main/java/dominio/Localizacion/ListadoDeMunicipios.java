package dominio.Localizacion;

import java.util.List;

public class ListadoDeMunicipios {
    public Integer cantidad;
    public Integer inicio;
    public Integer total;
    public Parametro parametros;
    public List<Municipio> municipios;

    private class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincia;
    }
}
