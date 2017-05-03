package mrs.domain.service.room;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.repository.room.MeetingRoomRepository;
import mrs.domain.repository.room.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RoomService {
    @Autowired
    ReservableRoomRepository reservableRoomRepository;

    @Autowired
    MeetingRoomRepository meetingRoomRepository;

    public List<ReservableRoom> findReservableRooms(LocalDate date) {
        return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
    }

    public MeetingRoom findMeetingRoom(Integer roomId) {
        return meetingRoomRepository.findOne(roomId);
    }
}
