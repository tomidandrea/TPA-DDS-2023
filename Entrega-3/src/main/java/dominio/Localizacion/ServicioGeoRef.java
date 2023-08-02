package dominio.Localizacion;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoRef {
    private static ServicioGeoRef instance = null;
    private Retrofit retrofit;

    private ServicioGeoRef() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.datos.gob.ar/georef/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ServicioGeoRef getInstance() {
        if (instance == null) {
            instance = new ServicioGeoRef();
        }
        return instance;
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeoRefService service = this.retrofit.create(GeoRefService.class);
        Call<ListadoDeProvincias> reqProvincias = service.getProvincias();
        Response<ListadoDeProvincias> respProvincias = reqProvincias.execute();
        return respProvincias.body();
    }

    public ListadoDeMunicipios listadoDeMunicipios() throws IOException {
        GeoRefService service = this.retrofit.create(GeoRefService.class);
        Call<ListadoDeMunicipios> reqMunicipios = service.getMunicipios();
        Response<ListadoDeMunicipios> respMunicipios = reqMunicipios.execute();
        return respMunicipios.body();
    }

}
