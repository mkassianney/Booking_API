package com.mkassianney.demo.Repository;

import br.com.caelum.stella.tinytype.CPF;
import com.mkassianney.demo.Model.Entities.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, CPF> {
    @NativeQuery(value = "SELECT * FROM clients WHERE cpf = ?1")
    Optional<Client> findByCpf(String s);
    boolean existsById(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM Client c WHERE c.cpf = :cpf")
    void deleteByCpf(@Param("cpf") String cpf);

    boolean existsByCpf(String cpf);
}
