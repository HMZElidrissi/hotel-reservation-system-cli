package repositories;

import models.Reservation;

import java.util.List;

public interface ReservationRepository {
    void create(Reservation reservation);
    void update(Reservation reservation);
    void cancel(int id);
    Reservation get(int id);
    List<Reservation> getAll();
}
