package mrs.domain.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Reservation implements Serializable {
  private static final long serialVersionUID = -6557775226647678958L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer reservationId;
  private LocalTime startTime;
  private LocalTime endTime;
  
  @ManyToOne
  @JoinColumns({@JoinColumn(name = "reserved_date"), @JoinColumn(name = "room_id")})
  private ReservableRoom reservableRoom;
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  public Integer getReservationId() {
    return reservationId;
  }
  
  public void setReservationId(Integer reservationId) {
    this.reservationId = reservationId;
  }
  
  public LocalTime getStartTime() {
    return startTime;
  }
  
  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }
  
  public LocalTime getEndTime() {
    return endTime;
  }
  
  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }
  
  public ReservableRoom getReservableRoom() {
    return reservableRoom;
  }
  
  public void setReservableRoom(ReservableRoom reservableRoom) {
    this.reservableRoom = reservableRoom;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  /**
   * Is Reserved.
   * @return  boolean
   */
  public boolean overlap(Reservation reservation) {
    
    ReservableRoomId reservedRoomId = reservableRoom.getReservableRoomId();
    ReservableRoomId willReserveRoomId = reservation.getReservableRoom().getReservableRoomId();
    
    if (!Objects.equals(reservedRoomId, willReserveRoomId)) {
      return false;
    }
    
    if (startTime.equals(reservation.startTime) && endTime.equals(reservation.endTime)) {
      return true;
    }
    
    return reservation.endTime.isAfter(startTime) && endTime.isAfter(reservation.startTime);
  }
}
