import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private int roomNumber;
    private List<Reservation> reservations;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.reservations = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public boolean isAvailable(Date checkInDate, Date checkOutDate) {
        for (Reservation reservation : reservations) {
            if (checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())) {
                return false;
            }
        }
        return true;
    }
}