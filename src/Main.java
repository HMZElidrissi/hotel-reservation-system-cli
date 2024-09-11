import utils.DatabaseConnection;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        displayMainMenu();
        scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // TODO: Create a reservation
                    break;
                case 2:
                    // TODO: Modify a reservation
                    break;
                case 3:
                    // TODO: Cancel a reservation
                    break;
                case 4:
                    // TODO: Display a reservation
                    break;
                case 5:
                    // TODO: Display all reservations
                    break;
                case 6:
                    // TODO: Check room availability
                    break;
                case 7:
                    // TODO: Display statistics
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
}