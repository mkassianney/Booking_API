package com.mkassianney.demo.Model.Repository;

import com.mkassianney.demo.Model.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @NativeQuery(value = "SELECT * FROM room WHERE room_number = ?1")
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
