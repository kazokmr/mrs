package mrs.domain.service.reservation;

public class UnavailableReservationException extends RuntimeException {
  private static final long serialVersionUID = 9044163931278942296L;
  
  public UnavailableReservationException(@SuppressWarnings("SameParameterValue") String message) {
    super(message);
  }
}
