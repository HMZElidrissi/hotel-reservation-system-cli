package enums;

import java.time.LocalDate;
import java.time.Month;

public enum Season {
    WINTER(Month.DECEMBER, 21, Month.MARCH, 20, 0.9f),
    SPRING(Month.MARCH, 21, Month.JUNE, 20, 1.1f),
    SUMMER(Month.JUNE, 21, Month.SEPTEMBER, 22, 1.3f),
    FALL(Month.SEPTEMBER, 23, Month.DECEMBER, 20, 1.0f);

    private final Month startMonth;
    private final int startDay;
    private final Month endMonth;
    private final int endDay;
    private final float rate;

    Season(Month startMonth, int startDay, Month endMonth, int endDay, float rate) {
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public static Season fromDate(LocalDate date) {
        for (Season season : Season.values()) {
            if (season.isDateInSeason(date)) {
                return season;
            }
        }
        throw new IllegalArgumentException("Incapable de trouver la saison pour la date spécifiée.");
    }

    private boolean isDateInSeason(LocalDate date) {
        LocalDate seasonStart = LocalDate.of(date.getYear(), startMonth, startDay);
        LocalDate seasonEnd = LocalDate.of(date.getYear(), endMonth, endDay);

        if (startMonth.getValue() > endMonth.getValue()) {
            return !date.isBefore(seasonStart) || !date.isAfter(seasonEnd);
        } else {
            return !date.isBefore(seasonStart) && !date.isAfter(seasonEnd);
        }
    }
}