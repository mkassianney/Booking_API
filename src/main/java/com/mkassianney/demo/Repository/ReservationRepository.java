package com.mkassianney.demo.Repository;

import com.mkassianney.demo.Model.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsById(int room_number);
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.room.roomNumber = :roomNumber AND (:checkIn < r.checkOutDate AND :checkOut > r.checkInDate)")
    boolean existsConflict(Integer roomNumber, LocalDate checkIn, LocalDate checkOut);

}
