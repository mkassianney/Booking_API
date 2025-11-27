package com.mkassianney.demo.Controller;

import com.mkassianney.demo.DTOs.ReservationData;
import com.mkassianney.demo.DTOs.ReservationDataList;
import com.mkassianney.demo.Repository.ReservationRepository;
import com.mkassianney.demo.Service.ReservationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/newReservation")
    @Transactional
    public void newReservation(@RequestBody @Valid ReservationData reservationData){
        reservationService.createReservation(reservationData);
    }

    @GetMapping("/reservationList")
    public Page<ReservationDataList> reservationList(@PageableDefault(size = 10, sort = {"roomNumber"}) Pageable pageable){
        return reservationRepository.findAll(pageable).map(ReservationDataList::new);
    }
}
