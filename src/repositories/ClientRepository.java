package repositories;

import models.Client;
import utils.EntityManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClientRepository extends GenericRepository<Client> {
    public ClientRepository() throws SQLException {
        super(Client.class, "clients");
    }

    public Client create(Client client) {
        return this.save(mapModelData(client));
    }

    public void update(Client client) {
        this.update(mapModelData(client), client.getId());
    }

    public Client get(int id) {
        return this.findById(id).orElse(null);
    }

    @Override
    protected Optional<Client> mapResultSetToModel(ResultSet resultSet) throws SQLException {
        return Optional.of(new Client(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("phone")
        ));
    }

    @Override
    protected Map<String, Object> mapModelData(Client client) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", client.getName());
        data.put("email", client.getEmail());
        data.put("phone", client.getPhone());
        return data;
    }
}
