package repositories;

import enums.RoomType;
import models.Reservation;
import models.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public Room getRoomByNumber(String number) {
        return this.findBy("room_number", number).orElse(null);
    }

    public List<String> getAvailableRoomNumbers(LocalDate checkIn, LocalDate checkOut) {
        String sql = "SELECT room_number FROM rooms WHERE id NOT IN " +
                "(SELECT DISTINCT room_id FROM reservations " +
                "WHERE check_in < ? AND check_out > ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, checkOut);
            stmt.setObject(2, checkIn);

            try (ResultSet rs = stmt.executeQuery()) {
                List<String> availableRoomNumbers = new ArrayList<>();
                while (rs.next()) {
                    availableRoomNumbers.add(rs.getString("room_number"));
                }
                return availableRoomNumbers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
