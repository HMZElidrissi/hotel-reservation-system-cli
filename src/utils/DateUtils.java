package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateUtils {
    public static LocalDate getDate(String input, Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        LocalDate currentDate = LocalDate.now();
        while (date == null) {
            System.out.print(input);
            String dateStr = scanner.nextLine();
            try {
                date = LocalDate.parse(dateStr, formatter);
                if (date.isBefore(currentDate)) {
                    System.out.println("Erreur: La date doit être ultérieure à la date actuelle.");
                    date = null;
                }
            } catch (Exception e) {
                System.out.println("Erreur: Format de date invalide ou date incorrecte.");
            }
        }
        return date;
    }
}
