package com.mkassianney.demo.Controller;

import com.mkassianney.demo.Model.DTOs.*;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Service.PaymentsService;
import com.mkassianney.demo.Model.Service.ReservationService;
import com.mkassianney.demo.Model.Entities.Room;
import com.mkassianney.demo.Model.Repository.ClientRepository;
import com.mkassianney.demo.Model.Repository.PaymentsRepository;
import com.mkassianney.demo.Model.Repository.ReservationRepository;
import com.mkassianney.demo.Model.Repository.RoomRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservations")
public class Register {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;
    @Autowired
    private PaymentsService paymentsService;
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/register")
    @Transactional
    public void register(@RequestBody @Valid ClientData clients){
        repository.save(new Client(clients));
    }

    @PostMapping("/newRoom")
    @Transactional
    public void newRoom(@RequestBody @Valid RoomData roomData){
        roomRepository.save(new Room(roomData));
    }

    @PostMapping("/newReservation")
    @Transactional
    public void newReservation(@RequestBody @Valid ReservationData reservationData){
        reservationService.createReservation(reservationData);
    }

    @GetMapping("/roomList")
    public Page<RoomDataList> roomList(@PageableDefault(size = 10, sort = {"roomNumber"}) Pageable pageable){
        return roomRepository.findAll(pageable).map(RoomDataList::new);
    }

    @GetMapping("/reservationList")
    public Page<ReservationDataList> reservationList(@PageableDefault(size = 10, sort = {"number"}) Pageable pageable){
        return reservationRepository.findAll(pageable).map(ReservationDataList::new);
    }


    @PostMapping("/toPay")
    @Transactional
    public void newPayment(@RequestBody @Valid PaymentData paymentData) throws Exception {
        paymentsService.createPayment(paymentData);
        paymentsService.processPayment(paymentData);
    }

    @GetMapping("/paymentList")
    public Page<PaymentDataList> paymentList(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        return paymentsRepository.findAll(pageable).map(PaymentDataList::new);
    }


}
