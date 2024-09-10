package daos;

import models.Reservation;
import java.util.Date;
import java.util.List;

public interface ReservationDAO extends GenericDAO<Reservation> {
    List<Reservation> findByClientId(Long clientId);
    List<Reservation> findByDateRange(Date startDate, Date endDate);
}
