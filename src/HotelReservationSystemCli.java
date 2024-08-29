import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationSystemCli {
    private static Hotel hotel;
    private static Scanner scanner;

    public static void main(String[] args) {
        Hotel hotelReservationSystem = new Hotel();
        int choice;
        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character, meaning read the newline character and discard it

            switch (choice) {
                case 1:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter check-in date (yyyy-MM-dd): ");
                    String checkInDateStr = scanner.nextLine();
                    Date checkInDate = parseDate(checkInDateStr);
                    System.out.print("Enter check-out date (yyyy-MM-dd): ");
                    String checkOutDateStr = scanner.nextLine();
                    Date checkOutDate = parseDate(checkOutDateStr);
                    hotelReservationSystem.createReservation(guestName, roomNumber, checkInDate, checkOutDate);
                    break;
                case 2:
                    // TODO: Implement modify reservation
                    break;
                case 3:
                    // TODO: Implement cancel reservation
                    break;
                case 4:
                    // TODO: Implement display reservation
                    break;
                case 5:
                    // TODO: Implement display all reservations
                    break;
                case 6:
                    // TODO: Implement check room availability
                    break;
                case 7:
                    System.out.println("Exiting Hotel X reservation system");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    private static void menu() {
        System.out.println("Welcome to Hotel X reservation system");
        System.out.println("======================================");
        System.out.println("1. Create Reservation");
        System.out.println("2. Modify Reservation");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Display Reservation");
        System.out.println("5. Display All Reservations");
        System.out.println("6. Check Room Availability");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format");
            return null;
        }
    }
}