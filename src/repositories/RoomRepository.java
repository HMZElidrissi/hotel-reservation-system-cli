package repositories;

import models.Room;
import utils.EntityManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class RoomRepository {
    private final EntityManager<Room> entityManager;

    public RoomRepository() throws SQLException {
        this.entityManager = new EntityManager<>(Room.class, "rooms");
    }

    public List<Room> getAll() {
        return entityManager.findAll(new HashMap<>());
    }

    public Room get(int id) {
        return entityManager.findById(id).orElse(null);
    }

    public Room getRoomByNumber(int number) {
        return entityManager.findBy("room_number", number).orElse(null);
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put("room_number", "NOT IN (SELECT room_id FROM reservations WHERE check_in <= ? AND check_out >= ?)");
        conditions.put("check_in", checkIn);
        conditions.put("check_out", checkOut);
        return entityManager.findAll(conditions);
    }
}
