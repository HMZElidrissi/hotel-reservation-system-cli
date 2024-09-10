package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final String url;
    private final String username;
    private final String password;

    private DatabaseConnection() {
        url = System.getenv("DB_URL");
        username = System.getenv("DB_USERNAME");
        password = System.getenv("DB_PASSWORD");

        if (url == null || username == null || password == null) {
            throw new RuntimeException("Les variables d'environnement de la base de données ne sont pas correctement définies");
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la connexion à la base de données", e);
        }
    }

    public void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Connexion à la base de données réussie");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données");
            e.printStackTrace();
        }
    }
}