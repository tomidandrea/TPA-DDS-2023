package converters;

import dominio.Notificacion.MedioCorreo;
import dominio.Notificacion.MedioDeComunicacion;
import dominio.Notificacion.MedioWhatsapp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MedioComunicacionString implements AttributeConverter<MedioDeComunicacion, String> {

  @Override
  public String convertToDatabaseColumn(MedioDeComunicacion medioDeComunicacion) {
    return medioDeComunicacion==null ? null : medioDeComunicacion.toString();
  }

  @Override
  public MedioDeComunicacion convertToEntityAttribute(String tipoMedioComunicacion) {
    return null; //TODO
  }
}
