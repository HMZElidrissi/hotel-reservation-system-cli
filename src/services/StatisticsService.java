package services;

import repositories.*;

import java.sql.SQLException;

public class StatisticsService {
    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

    public StatisticsService() throws SQLException {
        this.roomRepository = new RoomRepository();
        this.clientRepository = new ClientRepository();
        this.reservationRepository = new ReservationRepository();
    }

    public void getStatistics() {
        System.out.println("Number of rooms: " + roomRepository.count());
        System.out.println("Number of clients: " + clientRepository.count());
        System.out.println("Number of reservations: " + reservationRepository.count());
    }
}
