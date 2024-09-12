package services;

import repositories.ClientRepository;
import repositories.ReservationRepository;
import repositories.RoomRepository;

import java.time.LocalDate;
import enums.*;
import models.*;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository;
    private ClientRepository clientRepository;
    private RoomRepository roomRepository;

    public ReservationService() throws SQLException {
        this.reservationRepository = new ReservationRepository();
        this.clientRepository = new ClientRepository();
        this.roomRepository = new RoomRepository();
    }

    public Reservation createReservation(Client client, Room room, LocalDate checkIn, LocalDate checkOut, Event event) {
        Reservation reservation = new Reservation(client, room, checkIn, checkOut, ReservationStatus.COMPLETED, event);
        return reservationRepository.create(reservation);
    }

    public void modifyReservation(Reservation reservation) {
        reservationRepository.update(reservation);
    }

    public Reservation getReservation(int id) {
        return reservationRepository.get(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAll();
    }

    public void cancelReservation(int id) {
        reservationRepository.cancel(id);
    }
}
