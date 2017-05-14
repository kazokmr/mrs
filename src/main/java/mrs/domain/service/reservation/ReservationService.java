package mrs.domain.service.reservation;

import java.util.List;
import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {
  
  private final ReservationRepository reservationRepository;
  private final ReservableRoomRepository reservableRoomRepository;
  
  @Autowired
  public ReservationService(
      ReservationRepository reservationRepository,
      ReservableRoomRepository reservableRoomRepository
  ) {
    this.reservationRepository = reservationRepository;
    this.reservableRoomRepository = reservableRoomRepository;
  }
  
  public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
    return reservationRepository
        .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
  }
  
  /**
   * Save Reservation.
   * @param reservation reservation
   */
  public void reserve(Reservation reservation) {
    ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
    
    ReservableRoom reservableRoom =
        reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId);
    
    if (reservableRoom == null) {
      throw new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。");
    }
    
    boolean overlap =
        reservationRepository
            .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
            .stream().anyMatch(x -> x.overlap(reservation));
    
    if (overlap) {
      throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
    }
    
    reservationRepository.save(reservation);
  }
  
  @PreAuthorize("hasRole('ADMIN') or (#reservation.user.userId == principal.user.userId)")
  public void cancel(@P("reservation") Reservation reservation) {
    reservationRepository.delete(reservation);
  }
  
  public Reservation findOne(Integer reservationId) {
    return reservationRepository.findOne(reservationId);
  }
  
  //  public void cancel(Integer reservationId, User requestUser) {
  //    Reservation reservation = reservationRepository.findOne(reservationId);
  //
  //    if (
  //      RoleName.ADMIN != requestUser.getRoleName() &&
  //      !Objects.equals(reservation.getUser().getUserId(), requestUser.getUserId())
  //    ) {
  //      throw new AccessDeniedException("要求されたキャンセルは許可できません。");
  //    }
  //    reservationRepository.delete(reservation);
  //  }
}
