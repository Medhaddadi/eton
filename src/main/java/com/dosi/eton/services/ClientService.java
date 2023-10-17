package com.dosi.eton.services;



import com.dosi.eton.models.Client;

import java.util.List;
import java.util.Set;

public interface ClientService {
    Set<Client> getAllUtilisateurAudi();

    Client save(Client client);


}
