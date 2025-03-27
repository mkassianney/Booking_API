package com.mkassianney.demo.Model.Repository;

import com.mkassianney.demo.Model.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
