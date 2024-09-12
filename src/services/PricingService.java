package services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import enums.Season;
import models.Reservation;

public class PricingService {
    public static float calculatePrice(Reservation reservation) {
        float price = reservation.getRoom().getPrice();
        LocalDate checkIn = reservation.getCheckIn();
        LocalDate checkOut = reservation.getCheckOut();
        long totalDays = getTotalDays(checkIn, checkOut);
        float eventRate = reservation.getEvent().getRate();
        float roomTypeRate = reservation.getRoom().getRoomType().getRate();
        float daysRate = calculateDaysRate(checkIn, checkOut);
        return price * eventRate * daysRate * roomTypeRate * totalDays;
    }

    public static float caltculateRefund(Reservation reservation) {
        float price = calculatePrice(reservation);
        LocalDate checkIn = reservation.getCheckIn();
        LocalDate today = LocalDate.now();
        long totalDays = getTotalDays(checkIn, today);
        if (totalDays > 1) {
            return price * 0.5f;
        } else {
            return price * 0.2f;
        }
    }

    public static float calculateDaysRate(LocalDate startDate, LocalDate endDate) {
        float totalRate = 0;
        long totalDays = getTotalDays(startDate, endDate);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Season season = Season.fromDate(date);
            totalRate += season.getRate();
            if (date.getDayOfWeek().getValue() >= 6) {
                totalRate += 0.1f;
            }
        }

        // using streams
        /*
        float totalRate = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(totalDays)
                .map(date -> {
                    float rate = Season.fromDate(date).getRate();
                    if (date.getDayOfWeek().getValue() >= 6) {
                        rate += 0.1f;
                    }
                    return rate;
                })
                .reduce(0f, Float::sum);
        */

        return totalRate / totalDays;
    }

    public static long getTotalDays(LocalDate startDate, LocalDate endDate) {
        // long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return startDate.datesUntil(endDate).count();
    }
}