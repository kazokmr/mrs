package mrs.domain.model.converter;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@SuppressWarnings("WeakerAccess")
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {
  @Override
  public Time convertToDatabaseColumn(LocalTime localTime) {
    return localTime == null ? null : Time.valueOf(localTime);
  }
  
  @Override
  public LocalTime convertToEntityAttribute(Time time) {
    return time == null ? null : time.toLocalTime();
  }
}
