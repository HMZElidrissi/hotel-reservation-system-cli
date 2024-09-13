package repositories;

import enums.Event;
import enums.ReservationStatus;
import models.Client;
import models.Reservation;
import models.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReservationRepository extends GenericRepository<Reservation> {
    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;

    public ReservationRepository() throws SQLException {
        super(Reservation.class, "reservations");
        this.roomRepository = new RoomRepository();
        this.clientRepository = new ClientRepository();
    }

    public Reservation create(Reservation reservation) {
        return this.save(mapModelData(reservation));
    }

    public void update(Reservation reservation) {
        this.update(mapModelData(reservation), reservation.getId());
    }

    public void cancel(int id) {
        Reservation reservation = this.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            this.update(mapModelData(reservation), reservation.getId());
        }
    }

    public Reservation get(int id) {
        return this.findById(id).orElse(null);
    }

    public List<Reservation> getAll() {
        return this.findAll(new HashMap<>());
    }

    @Override
    protected Optional<Reservation> mapResultSetToModel(ResultSet resultSet) throws SQLException {
        Client client = this.clientRepository.get(resultSet.getInt("client_id"));
        Room room = this.roomRepository.get(resultSet.getInt("room_id"));
        Event event = Event.fromString(resultSet.getString("event"));
        return Optional.of(new Reservation(
                resultSet.getInt("id"),
                client,
                room,
                resultSet.getDate("check_in").toLocalDate(),
                resultSet.getDate("check_out").toLocalDate(),
                ReservationStatus.valueOf(resultSet.getString("status")),
                event
        ));
    }
}