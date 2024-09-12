package models;

import java.time.LocalDate;
import java.util.Date;

import enums.Event;
import enums.ReservationStatus;
import enums.Season;

public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ReservationStatus status;
    private Event event;

    public Reservation(Client client, Room room, LocalDate checkIn, LocalDate checkOut, ReservationStatus status, Event event) {
        this.client = client;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return String.format(
                "Reservation Details:\n" +
                        "-------------------\n" +
                        "ID: %d\n" +
                        "Client: %s\n" +
                        "Room: %s\n" +
                        "Check-in: %s\n" +
                        "Check-out: %s\n" +
                        "Status: %s\n" +
                        "Event: %s" +
                        "\n-------------------\n",
                id,
                client.getName(),
                room.getRoomNumber(),
                checkIn,
                checkOut,
                status,
                event
        );
    }
}
