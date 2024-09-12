package models;

import enums.RoomType;

import java.util.List;

public class Room {
    private int id;
    private String roomNumber;
    private RoomType roomType;
    private float price;
    private List<Reservation> reservations;

    public Room(String roomNumber, RoomType roomType, Long price, List<Reservation> reservations) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return String.format(
                "Room Details:\n" +
                        "-----------------\n" +
                        "Room Number: %s\n" +
                        "Room Type: %s\n" +
                        "Price: %s\n" +
                        "-----------------\n",
                this.roomNumber, this.roomType, this.price);
    }
}
