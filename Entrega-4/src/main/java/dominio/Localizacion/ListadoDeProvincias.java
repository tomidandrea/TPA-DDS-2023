package dominio.Localizacion;
import java.util.List;

public class ListadoDeProvincias {
    public Integer cantidad;
    public Integer total;
    public Integer inicio;
    public Parametro parametros;
    public List<Provincia> provincias;

    private class Parametro{
        public List<String> campos;
    }
}
