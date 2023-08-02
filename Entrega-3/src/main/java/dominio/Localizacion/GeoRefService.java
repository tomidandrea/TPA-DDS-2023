package dominio.Localizacion;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GeoRefService {
    @GET("provincias")
    Call<ListadoDeProvincias> getProvincias();

    @GET("municipios")
    Call<ListadoDeMunicipios> getMunicipios();

    @GET("departamentos")
    Call<ListadoDeDepartamentos> getDepartamentos();

    /*@GET("localidades")
    Call<ListadoDeLocalidades> getLocalidades();
*/

}
