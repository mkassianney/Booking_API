package com.mkassianney.demo.Model.Repository;

import br.com.caelum.stella.tinytype.CPF;
import com.mkassianney.demo.Model.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, CPF> {
    @NativeQuery(value = "SELECT * FROM client WHERE cpf = ?1")
    Optional<Client> findByCpf(String s);
}
