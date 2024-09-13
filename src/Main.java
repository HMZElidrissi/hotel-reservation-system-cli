import enums.Event;
import models.Client;
import models.Reservation;
import models.Room;
import services.*;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static ReservationService reservationService;
    private static ClientService clientService;
    private static RoomService roomService;
    private static StatisticsService statisticsService;

    public static void main(String[] args) {
        try {
            reservationService = new ReservationService();
            clientService = new ClientService();
            roomService = new RoomService();
            statisticsService = new StatisticsService();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation des services.");
            e.printStackTrace();
            return;
        }
        scanner = new Scanner(System.in);
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createReservation(clientService, reservationService, roomService);
                    break;
                case 2:
                    modifyReservation(reservationService, roomService);
                    break;
                case 3:
                    cancelReservation(reservationService);
                    break;
                case 4:
                    displayReservation(reservationService);
                    break;
                case 5:
                    displayAllReservations(reservationService);
                    break;
                case 6:
                    checkRoomAvailability(roomService);
                    break;
                case 7:
                    statisticsService.getStatistics();
                    break;
                case 8:
                    System.out.println("Fermeture de l'application.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 8);

    }

    private static void displayMainMenu() {
        System.out.println("\n=== Bienvenue à l'hôtel X ===");
        System.out.println("=== Veuillez choisir une option: ===");
        System.out.println("1. Créer une réservation");
        System.out.println("2. Modifier une réservation");
        System.out.println("3. Annuler une réservation");
        System.out.println("4. Afficher une réservation");
        System.out.println("5. Afficher toutes les réservations");
        System.out.println("6. Vérifier la disponibilité d'une chambre");
        System.out.println("7. Statistiques");
        System.out.println("8. Quitter");
    }

    public static void createReservation(ClientService clientService, ReservationService reservationService, RoomService roomService) {
        System.out.println("Veuillez entrer les informations suivantes:");
        System.out.print("Nom du client: ");
        String clientName = scanner.nextLine();
        System.out.print("Email du client: ");
        String clientEmail = scanner.nextLine();
        System.out.print("Téléphone du client: ");
        String clientPhone = scanner.nextLine();
        Client createdClient = clientService.createClient(clientName, clientEmail, clientPhone);

        LocalDate checkIn = DateUtils.getDate("Date d'arrivée (jj/mm/aaaa): ", scanner);
        LocalDate checkOut = DateUtils.getDate("Date de départ (jj/mm/aaaa): ", scanner);
        System.out.print("\nÉvénement (anniversaire/conférence/réunion/rien): \n");
        for (Event event : Event.values()) {
            System.out.println(event.ordinal() + 1 + ". " + event);
        }
        int eventChoice = scanner.nextInt();
        scanner.nextLine();
        Event event = Event.values()[eventChoice - 1];
        System.out.print("\nChoisissez le numéro de la chambre: \n");
        roomService.getRooms();
        String roomNumber = scanner.nextLine();
        Room selectedRoom = roomService.getRoomByNumber(roomNumber);
        Reservation createdReservation = reservationService.createReservation(createdClient, selectedRoom, checkIn, checkOut, event);
        System.out.println("\nRéservation créée avec succès.\n");
        float price = PricingService.calculatePrice(createdReservation);
        System.out.println("\nLe prix total est de: " + price + " MAD\n");
    }

    public static void modifyReservation(ReservationService reservationService, RoomService roomService) {
        System.out.println("\nVeuillez entrer l'ID de la réservation à modifier: \n");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        Reservation reservation = reservationService.getReservation(reservationId);
        LocalDate checkIn = DateUtils.getDate("La nouvelle date d'arrivée (jj/mm/aaaa): ", scanner);
        LocalDate checkOut = DateUtils.getDate("La nouvelle date de départ (jj/mm/aaaa): ", scanner);
        System.out.print("Choisissez le numéro de la nouvelle chambre: ");
        roomService.getRooms();
        String roomNumber = scanner.nextLine();

        Room selectedRoom = roomService.getRoomByNumber(roomNumber);
        System.out.print("\nÉvénement (anniversaire/conférence/réunion/rien): \n");
        for (Event event : Event.values()) {
            System.out.println(event.ordinal() + 1 + ". " + event);
        }
        int eventChoice = scanner.nextInt();
        scanner.nextLine();
        Event event = Event.values()[eventChoice - 1];
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        reservation.setRoom(selectedRoom);
        reservation.setEvent(event);
        reservationService.modifyReservation(reservation);
        System.out.println("\nRéservation modifiée avec succès.\n");
        float price = PricingService.calculatePrice(reservation);
        System.out.println("Le nouveau prix total est de: " + price + " MAD\n");
    }

    public static void cancelReservation(ReservationService reservationService) {
        System.out.println("\nVeuillez entrer l'ID de la réservation à annuler: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        reservationService.cancelReservation(reservationId);
        System.out.println("\nRéservation annulée avec succès.");
        float refund = PricingService.caltculateRefund(reservationService.getReservation(reservationId));
        System.out.println("\nLe montant du remboursement est de: " + refund + " MAD");
    }

    public static void displayReservation(ReservationService reservationService) {
        System.out.println("\nVeuillez entrer l'ID de la réservation à afficher: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        Reservation reservation = reservationService.getReservation(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("\nRéservation introuvable.");
        }
    }

    public static void displayAllReservations(ReservationService reservationService) {
        reservationService.getAllReservations().forEach(System.out::println);
    }

    public static void checkRoomAvailability(RoomService roomService) {
        System.out.println("\nVeuillez entrer les dates de réservation: ");
        LocalDate checkIn = DateUtils.getDate("\nDate d'arrivée (jj/mm/aaaa): ", scanner);
        LocalDate checkOut = DateUtils.getDate("\nDate de départ (jj/mm/aaaa): ", scanner);
        List<String> availableRoomNumbers = roomService.getAvailableRooms(checkIn, checkOut);
        if (availableRoomNumbers.isEmpty()) {
            System.out.println("\nAucune chambre disponible pour ces dates.");
        } else {
            System.out.println("\nLes chambres suivantes sont disponibles: ");
            availableRoomNumbers.stream()
                    .map(roomNumber -> roomService.getRoomByNumber(roomNumber))
                    .forEach(System.out::println);
        }
    }
}