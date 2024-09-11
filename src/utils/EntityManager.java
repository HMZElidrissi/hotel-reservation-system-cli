package utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EntityManager<T> {
    private Connection connection;
    private Class<T> modelClass;
    private String tableName;

    public EntityManager(Class<T> modelClass, String tableName) throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.modelClass = modelClass;
        this.tableName = tableName;
    }

    public List<T> findAll(Map<String, Object> conditions) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName);
        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions.keySet().stream().map(key -> key + " = ?").collect(Collectors.toList())));
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object value : conditions.values()) {
                stmt.setObject(index++, value);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                List<T> objects = new ArrayList<>();
                while (rs.next()) {
                    mapResultSetToModel(rs).ifPresent(objects::add);
                }
                return objects;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<T> findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToModel(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void save(Map<String, Object> data) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        sql.append(String.join(", ", data.keySet()));
        sql.append(") VALUES (");
        sql.append(String.join(", ", data.keySet().stream().map(key -> "?").toArray(String[]::new)));
        sql.append(")");

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object value : data.values()) {
                statement.setObject(index++, value);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Map<String, Object> data, int id) {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        sql.append(String.join(", ", data.keySet().stream().map(key -> key + " = ?").toArray(String[]::new)));
        sql.append(" WHERE id = ?");

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object value : data.values()) {
                statement.setObject(index++, value);
            }
            statement.setInt(index, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<T> mapResultSetToModel(ResultSet resultSet) throws SQLException {
        try {
            T entity = modelClass.getDeclaredConstructor().newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                Field field = modelClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(entity, value);
            }

            return Optional.of(entity);
        } catch (ReflectiveOperationException e) {
            throw new SQLException("Erreur lors du mapping du ResultSet vers le modèle", e);
        }
    }
}
