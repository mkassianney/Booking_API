package com.mkassianney.demo.Controller;

import com.mkassianney.demo.DTOs.RoomData;
import com.mkassianney.demo.DTOs.RoomDataList;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/newRoom")
    @Transactional
    public void newRoom(@RequestBody @Valid RoomData roomData){
        roomRepository.save(new Room(roomData));
    }

    @GetMapping("/roomList")
    public Page<RoomDataList> roomList(@PageableDefault(size = 10, sort = {"roomNumber"}) Pageable pageable){
        return roomRepository.findAll(pageable).map(RoomDataList::new);
    }

}
