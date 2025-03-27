package com.mkassianney.demo.Model.Repository;

import com.mkassianney.demo.Model.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
