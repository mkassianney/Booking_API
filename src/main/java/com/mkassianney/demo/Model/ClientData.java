package com.mkassianney.demo.Model;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record ClientData(
        @Column(unique = true) @NotBlank @Valid String name,
        @Column(unique = true) @CPF(message = "CPF isn't valid") @NotBlank @Valid String cpf,
        @Column(unique = true) @Email @NotBlank @Valid String email,
        @Column(unique = true) @NotBlank @Valid @Pattern(regexp = "\\d{11}", message = "That cellphone number isn't valid.") String cellphone) {
}
