package mrs.app.reservation;

import java.time.LocalTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ThirtyMinutesUnitValidator implements ConstraintValidator<ThirtyMinutesUnit, LocalTime> {
  @Override
  public void initialize(ThirtyMinutesUnit constraintAnnotation) {
  
  }
  
  @Override
  public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
    return value == null || value.getMinute() % 30 == 0;
  }
}
