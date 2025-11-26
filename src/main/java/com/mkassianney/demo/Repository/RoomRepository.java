package com.mkassianney.demo.Repository;

import com.mkassianney.demo.Model.Entities.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @NativeQuery(value = "SELECT * FROM rooms WHERE room_number = ?")
    Optional<Room> findByRoomNumber(Integer roomNumber);

    @EntityGraph(attributePaths = "reservations")
    @Query("SELECT r FROM Room r")
    List<Room> findAllWithReservations();


}
