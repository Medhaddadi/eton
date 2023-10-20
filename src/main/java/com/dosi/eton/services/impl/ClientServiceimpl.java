package com.dosi.eton.services.impl;

import com.dosi.eton.models.Client;
import com.dosi.eton.models.STATUS;
import com.dosi.eton.repository.ClientRepository;
import com.dosi.eton.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientServiceimpl implements ClientService {

    private final ClientRepository ClientRepository;


    @Override
    public Set<Client> getAllUtilisateurAudi() {
        return new HashSet<>(ClientRepository.findAll());
    }

    @Override
    public Client save(Client client) {
        return ClientRepository.save(client);
    }

    @Override
    public Client getClientById(Integer idClient) {
        try {
            return ClientRepository.findById(Long.valueOf(idClient)).get();
        } catch (Exception e) {
            throw new RuntimeException("Client non trouvé");
        }
    }

    @Override
    public Client getByEmail(String username) {
        return ClientRepository.findByEmail(username);
    }

    @Override
    public Client findById(Long aLong) {
        if (ClientRepository.findById(aLong).isPresent()) {
            return ClientRepository.findById(aLong).get();
        }else {
            throw new RuntimeException("Client non trouvé");
        }
    }


}
