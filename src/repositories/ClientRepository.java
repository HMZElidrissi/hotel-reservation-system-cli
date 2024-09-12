package repositories;

import models.Client;
import utils.EntityManager;

import java.sql.SQLException;
import java.util.*;

public class ClientRepository {
    private final EntityManager<Client> entityManager; // final because it should not be changed after initialization

    public ClientRepository() throws SQLException {
        this.entityManager = new EntityManager<>(Client.class, "clients");
    }

    public Client create(Client client) {
        return entityManager.save(mapModelData(client));
    }

    public void update(Client client) {
        entityManager.update(mapModelData(client), client.getId());
    }

    public Client get(int id) {
        return entityManager.findById(id).orElse(null);
    }

    private Map<String, Object> mapModelData(Client client) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", client.getName());
        data.put("email", client.getEmail());
        data.put("phone", client.getPhone());
        return data;
    }

    public long count() {
        return entityManager.count();
    }
}
