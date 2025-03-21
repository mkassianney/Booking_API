package com.mkassianney.demo.Model.Repository;

import br.com.caelum.stella.tinytype.CPF;
import com.mkassianney.demo.Model.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, CPF> {
}
