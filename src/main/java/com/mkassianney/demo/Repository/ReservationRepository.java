package com.mkassianney.demo.Repository;

import com.mkassianney.demo.DTOs.ReservationSimpleResponse;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsById(@Param("id") Long id);
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.room.roomNumber = :roomNumber AND (:checkIn < r.checkOutDate AND :checkOut > r.checkInDate)")
    boolean existsConflict(Integer roomNumber, LocalDate checkIn, LocalDate checkOut);

    @Query(value = "SELECT r FROM Reservation r WHERE r.client.id = :id")
    List<ReservationSimpleResponse> findListByClientId(@Param("id") Long id);

}
