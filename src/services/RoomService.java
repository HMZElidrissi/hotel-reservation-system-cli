package services;

import models.Room;
import repositories.RoomRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService() throws SQLException {
        this.roomRepository = new RoomRepository();
    }

    public void getRooms() {
        roomRepository.getAll().forEach(System.out::println);
    }

    public Room getRoomByNumber(int number) {
        return roomRepository.getRoomByNumber(number);
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        return roomRepository.getAvailableRooms(checkIn, checkOut);
    }
}
