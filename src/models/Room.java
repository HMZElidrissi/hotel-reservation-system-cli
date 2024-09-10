package models;

import enums.RoomType;

import java.util.List;

public class Room extends Model{
    private String roomNumber;
    private RoomType roomType;
    private List<Reservation> reservations;

    public Room(String roomNumber, RoomType roomType) {
        super("rooms");
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
