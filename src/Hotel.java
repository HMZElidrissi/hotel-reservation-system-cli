import java.util.*;

public class Hotel {
    private static final int NUM_ROOMS = 10;
    private Room[] rooms;

    public Hotel() {
        rooms = new Room[NUM_ROOMS];
        for (int i = 0; i < NUM_ROOMS; i++) {
            rooms[i] = new Room(i + 1);
        }
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void createReservation(String guestName, int roomNumber, Date checkInDate, Date checkOutDate) {
        Room room = rooms[roomNumber - 1];
        if (room.isAvailable(checkInDate, checkOutDate)) {
            Reservation reservation = new Reservation(guestName, room, checkInDate, checkOutDate);
            room.addReservation(reservation);
            System.out.println("Reservation created successfully with ID: " + reservation.getId());
        } else {
            System.out.println("Room is not available");
        }
    }

    public Reservation findReservation(int reservationId) {
        return Arrays.stream(rooms)
                .flatMap(room -> room.getReservations().stream())
                .filter(reservation -> reservation.getId() == reservationId)
                .findFirst()
                .orElse(null);
    }

    public void modifyReservation(int reservationId, String guestName, int roomNumber, Date checkInDate, Date checkOutDate) {
        Reservation reservation = findReservation(reservationId);
        if (reservation != null) {
            Room newRoom = rooms[roomNumber - 1];
            if (newRoom.isAvailable(checkInDate, checkOutDate) || newRoom == reservation.getRoom()) {
                reservation.cancelReservation();
                reservation.setGuestName(guestName);
                reservation.setRoom(newRoom);
                reservation.setCheckInDate(checkInDate);
                reservation.setCheckOutDate(checkOutDate);
                newRoom.addReservation(reservation);
                System.out.println("Reservation modified successfully");
            } else {
                System.out.println("Room is not available for the specified dates");
            }
        } else {
            System.out.println("Reservation not found");
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = findReservation(reservationId);
        if (reservation != null) {
            reservation.cancelReservation();
            System.out.println("Reservation cancelled successfully");
        } else {
            System.out.println("Reservation not found");
        }
    }

    public void checkRoomAvailability(Date checkInDate, Date checkOutDate) {
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                System.out.println("Room " + room.getRoomNumber() + " is available");
            } else {
                System.out.println("Room " + room.getRoomNumber() + " is not available");
            }
        }
    }
}
