package com.dosi.eton.services.impl;

import com.dosi.eton.models.Client;
import com.dosi.eton.models.STATUS;
import com.dosi.eton.repository.ClientRepository;
import com.dosi.eton.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceimpl implements ClientService {

    private final ClientRepository ClientRepository;

    public ClientServiceimpl(ClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }
    @Override
    public Set<Client> getAllUtilisateurAudi() {
        return new HashSet<>(ClientRepository.findAll());
    }

    @Override
    public Client save(Client client) {
        return ClientRepository.save(client);
    }



}
