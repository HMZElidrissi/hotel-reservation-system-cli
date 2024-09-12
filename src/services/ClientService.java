package services;

import models.Client;
import repositories.ClientRepository;

import java.sql.SQLException;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService() throws SQLException {
        this.clientRepository = new ClientRepository();
    }

    public Client createClient(String name, String email, String phone) {
        return clientRepository.create(new Client(name, email, phone));
    }

    public Client getClient(int id) {
        return clientRepository.get(id);
    }
}
