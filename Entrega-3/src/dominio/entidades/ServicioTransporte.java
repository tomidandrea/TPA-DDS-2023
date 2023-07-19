package dominio.entidades;

public class ServicioTransporte {
    MedioDeTransporte tipoTransporte;
    Linea lineaDeTransporteIda;
    Linea lineaDeTransporteVuelta;

    public ServicioTransporte setTipoTransporte(MedioDeTransporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;

    }
}
