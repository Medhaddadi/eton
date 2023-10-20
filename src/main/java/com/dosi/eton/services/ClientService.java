package com.dosi.eton.services;



import com.dosi.eton.models.Client;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ClientService {
    Set<Client> getAllUtilisateurAudi();

    Client save(Client client);


   Client getClientById(Integer idClient);

    Client getByEmail(String username);

    Client findById(Long aLong);
}
