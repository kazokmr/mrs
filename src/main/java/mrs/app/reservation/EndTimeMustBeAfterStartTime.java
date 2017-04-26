package mrs.app.reservation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EndTimeMustBeAfterStartTimeValidator.class})
@Target({ElementType.TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface EndTimeMustBeAfterStartTime {
    String message() default "{mrs.app.reservation.EndTimeMustBeAfterStartTime.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        EndTimeMustBeAfterStartTime[] value();
    }
}
