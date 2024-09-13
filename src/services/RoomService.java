package services;

import models.Room;
import repositories.RoomRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService() throws SQLException {
        this.roomRepository = new RoomRepository();
    }

    public void getRooms() {
        roomRepository.getAll().forEach(System.out::println);
    }

    public Room getRoomByNumber(String roomNumber) {
        return roomRepository.getRoomByNumber(roomNumber);
    }

    public List<String> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        return roomRepository.getAvailableRoomNumbers(checkIn, checkOut);
    }
}
