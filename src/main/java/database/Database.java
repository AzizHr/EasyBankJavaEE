package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    private static Database instance;

    private Database() {
        try {
            String url = "jdbc:postgresql://localhost:5432/easy_bank";
            String username = "postgres";
            String password = "aziz";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
//            System.out.println("success");
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
