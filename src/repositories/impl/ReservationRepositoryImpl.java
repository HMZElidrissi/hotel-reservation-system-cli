package repositories.impl;

import repositories.ReservationRepository;
import enums.ReservationStatus;
import models.Reservation;
import utils.EntityManager;

import java.sql.SQLException;
import java.util.*;

public class ReservationRepositoryImpl implements ReservationRepository {
    private final EntityManager<Reservation> entityManager; // final because it should not be changed after initialization

    public ReservationRepositoryImpl(EntityManager<Reservation> entityManager) throws SQLException {
        this.entityManager = new EntityManager<>(Reservation.class, "reservations");
    }

    @Override
    public void create(Reservation reservation) {
        entityManager.save(mapModelData(reservation));
    }

    @Override
    public void update(Reservation reservation) {
        entityManager.update(mapModelData(reservation), reservation.getId());
    }

    private Map<String, Object> mapModelData(Reservation reservation) {
        Map<String, Object> data = new HashMap<>();
        data.put("client_id", reservation.getClient().getId());
        data.put("room_id", reservation.getRoom().getId());
        data.put("check_in", reservation.getCheckIn());
        data.put("check_out", reservation.getCheckOut());
        data.put("status", reservation.getStatus());
        data.put("season", reservation.getSeason());
        data.put("event", reservation.getEvent());
        return data;
    }

    @Override
    public void cancel(int id) {
        Reservation reservation = entityManager.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            entityManager.update(mapModelData(reservation), reservation.getId());
        }
    }

    @Override
    public Reservation get(int id) {
        return entityManager.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> getAll() {
        return entityManager.findAll(new HashMap<>());
    }
}
