package mrs.domain.model.converter;

import static java.sql.Date.valueOf;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@SuppressWarnings("WeakerAccess")
@Converter(autoApply = true)
public class LocalDataConverter implements AttributeConverter<LocalDate, java.sql.Date> {
  
  @Override
  public Date convertToDatabaseColumn(LocalDate localDate) {
    return localDate == null ? null : valueOf(localDate);
  }
  
  @Override
  public LocalDate convertToEntityAttribute(Date date) {
    return date == null ? null : date.toLocalDate();
  }
}
