import java.util.*;

public class Hotel {
    private static final int NUM_ROOMS = 10;
    private Room[] rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new Room[NUM_ROOMS];
        for (int i = 0; i < NUM_ROOMS; i++) {
            rooms[i] = new Room(i + 1);
        }
        reservations = new ArrayList<>();
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void createReservation(String guestName, int roomNumber, Date checkInDate, Date checkOutDate) {
        Room room = rooms[roomNumber - 1];
        if (room.isAvailable()) {
            room.setAvailable(false);
            Reservation reservation = new Reservation(guestName, room, checkInDate, checkOutDate);
            reservations.add(reservation);
            System.out.println("Reservation created successfully");
        } else {
            System.out.println("Room is not available");
        }
    }
}
