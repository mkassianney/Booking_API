package com.mkassianney.demo.Service;

import com.mkassianney.demo.Repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional
    public void deleteByCpf(String cpf) {
        boolean exists = repository.existsByCpf(cpf);

        if (!exists) {
            throw new EntityNotFoundException("Client already deleted or not found with CPF: " + cpf);
        }

        repository.deleteByCpf(cpf);
    }
}
