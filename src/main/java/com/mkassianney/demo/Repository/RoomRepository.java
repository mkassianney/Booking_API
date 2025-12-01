package com.mkassianney.demo.Repository;

import com.mkassianney.demo.Model.Entities.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @NativeQuery(value = "SELECT * FROM rooms WHERE room_number = ?")
    Optional<Room> findByRoomNumber(Integer roomNumber);

    boolean existsByRoomNumber(int roomNumber);

    @EntityGraph(attributePaths = "reservations")
    @Query("SELECT r FROM Room r")
    List<Room> findAllWithReservations();

    @Modifying(clearAutomatically = true)
    @Transactional
    @NativeQuery(value = "DELETE FROM rooms WHERE room_number = ?")
    void deleteByRoomNumber(int number);

}
