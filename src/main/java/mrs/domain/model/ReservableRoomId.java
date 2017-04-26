package mrs.domain.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class ReservableRoomId implements Serializable {
    private static final long serialVersionUID = 1992666431015096742L;

    private Integer roomId;
    private LocalDate reservedDate;

    public ReservableRoomId(Integer roomId, LocalDate reservedDate) {
        this.roomId = roomId;
        this.reservedDate = reservedDate;
    }

    public ReservableRoomId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservableRoomId that = (ReservableRoomId) o;

        if (!roomId.equals(that.roomId)) return false;
        return reservedDate.equals(that.reservedDate);
    }

    @Override
    public int hashCode() {
        int result = roomId.hashCode();
        result = 31 * result + reservedDate.hashCode();
        return result;
    }
}
