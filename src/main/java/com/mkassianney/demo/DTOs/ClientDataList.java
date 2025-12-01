package com.mkassianney.demo.DTOs;

import com.mkassianney.demo.Model.Entities.Client;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record ClientDataList (
        @NotNull String name,
        @NotNull String cpf,
        @NotNull String email,
        @NotNull String cellphone,
        List<ReservationSimpleResponse> reservation
) {

    public ClientDataList(Client data, List<ReservationSimpleResponse> reservation){
        this(data.getName(),
                data.getCpf(),
                data.getEmail(),
                data.getCellphone(),
                reservation);
    }

    public static ClientDataList from(Client client) {
        List<ReservationSimpleResponse> mapped = client.getReservations()
                .stream()
                .map(ReservationSimpleResponse::from)
                .collect(Collectors.toList());

        return new ClientDataList(client, mapped);
    }

}
