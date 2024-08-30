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

    public Reservation findReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    public void modifyReservation(int reservationId, String guestName, int roomNumber, Date checkInDate, Date checkOutDate) {
        Reservation reservation = findReservation(reservationId);
        if (reservation != null) {
            Room room = rooms[roomNumber - 1];
            if (room.isAvailable()) {
                reservation.setGuestName(guestName);
                reservation.setRoom(room);
                reservation.setCheckInDate(checkInDate);
                reservation.setCheckOutDate(checkOutDate);
                System.out.println("Reservation modified successfully");
            } else {
                System.out.println("Room is not available");
            }
        } else {
            System.out.println("Reservation not found");
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = findReservation(reservationId);
        if (reservation != null) {
            reservation.getRoom().setAvailable(true);
            reservations.remove(reservation);
            System.out.println("Reservation cancelled successfully");
        } else {
            System.out.println("Reservation not found");
        }
    }

    public void checkRoomAvailability(Date checkInDate, Date checkOutDate) {
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room " + room.getRoomNumber() + " is available");
            } else {
                boolean isAvailable = true;
                for (Reservation reservation : reservations) {
                    if (reservation.getRoom().getRoomNumber() == room.getRoomNumber()) {
                        if (checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())) {
                            isAvailable = false;
                            break;
                        }
                    }
                }
                if (isAvailable) {
                    System.out.println("Room " + room.getRoomNumber() + " is available");
                }
            }
        }
    }
}
