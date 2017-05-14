package mrs.domain.service.reservation;

public class AlreadyReservedException extends RuntimeException {
  private static final long serialVersionUID = -4745523761146600645L;
  
  public AlreadyReservedException(@SuppressWarnings("SameParameterValue") String message) {
    super(message);
  }
}
