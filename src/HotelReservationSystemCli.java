import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationSystemCli {
    private static Hotel hotel;
    private static Scanner scanner;

    public static void main(String[] args) {
        hotel = new Hotel();
        scanner = new Scanner(System.in);
        int choice;
        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createReservation();
                    break;
                case 2:
                    modifyReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    displayReservation();
                    break;
                case 5:
                    displayAllReservations();
                    break;
                case 6:
                    checkRoomAvailability();
                    break;
                case 7:
                    System.out.println("Exiting Hotel X reservation system");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
        scanner.close();
    }

    private static void createReservation() {
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        Date checkInDate = getDate("Enter check-in date (dd-MM-yyyy): ");
        Date checkOutDate = getDate("Enter check-out date (dd-MM-yyyy): ");
        hotel.createReservation(guestName, roomNumber, checkInDate, checkOutDate);
    }

    private static void modifyReservation() {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        Reservation reservation = hotel.findReservation(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
            System.out.print("Enter new guest name: ");
            String guestName = scanner.nextLine();
            System.out.print("Enter new room number: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();
            Date checkInDate = getDate("Enter new check-in date (dd-MM-yyyy): ");
            Date checkOutDate = getDate("Enter new check-out date (dd-MM-yyyy): ");
            hotel.modifyReservation(reservationId, guestName, roomNumber, checkInDate, checkOutDate);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        hotel.cancelReservation(reservationId);
    }

    private static void displayReservation() {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        Reservation reservation = hotel.findReservation(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static void displayAllReservations() {
        Arrays.stream(hotel.getRooms())
                .flatMap(room -> room.getReservations().stream())
                .forEach(System.out::println);
    }

    private static void checkRoomAvailability() {
        Date checkInDate = getDate("Enter check-in date (dd-MM-yyyy): ");
        Date checkOutDate = getDate("Enter check-out date (dd-MM-yyyy): ");
        hotel.checkRoomAvailability(checkInDate, checkOutDate);
    }

    private static void menu() {
        System.out.println("\nWelcome to Hotel X reservation system");
        System.out.println("======================================");
        System.out.println("1. Create Reservation");
        System.out.println("2. Modify Reservation");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Display Reservation");
        System.out.println("5. Display All Reservations");
        System.out.println("6. Check Rooms Availability");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Date getDate(String input) {
        Date date = null;
        Date currentDate = new Date();
        while (date == null) {
            System.out.print(input);
            String dateStr = scanner.nextLine();
            date = parseDate(dateStr);
            if (date != null && date.before(currentDate)) {
                System.out.println("Error: The date cannot be in the past. Please enter a future date.");
                date = null;
            }
        }
        return date;
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in dd-MM-yyyy format");
            return null;
        }
    }
}