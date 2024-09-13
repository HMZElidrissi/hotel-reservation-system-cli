package repositories;

import enums.RoomType;
import models.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RoomRepository extends GenericRepository<Room> {
    public RoomRepository() throws SQLException {
        super(Room.class, "rooms");
    }

    public List<Room> getAll() {
        return this.findAll(new HashMap<>());
    }

    public Room get(int id) {
        return this.findById(id).orElse(null);
    }

    public Room getRoomByNumber(int number) {
        return this.findBy("room_number", number).orElse(null);
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put("room_number", "NOT IN (SELECT room_id FROM reservations WHERE check_in <= ? AND check_out >= ?)");
        conditions.put("check_in", checkIn);
        conditions.put("check_out", checkOut);
        return this.findAll(conditions);
    }

    @Override
    protected Optional<Room> mapResultSetToModel(ResultSet resultSet) throws SQLException {
        RoomType roomType = RoomType.fromString(resultSet.getString("room_type"));
        return Optional.of(new Room(
                resultSet.getInt("id"),
                resultSet.getString("room_number"),
                roomType,
                resultSet.getLong("price")
        ));
    }
}
