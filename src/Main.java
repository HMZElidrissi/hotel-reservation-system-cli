import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.getInstance().testConnection();
    }
}