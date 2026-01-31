package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Supplier;

public class UserService {

    private static final String DB_URL = "jdbc:mysql://localhost/db";
    private static final String DB_USER = "root";

    private final String password;
    private final Supplier<Connection> connectionSupplier;

    // Production constructor
    public UserService() {
        this(System.getenv("DB_PASSWORD"));
    }

    // Production constructor with injected password
    public UserService(String password) {
        this.password = password;
        this.connectionSupplier = () -> {
            try {
                return DriverManager.getConnection(DB_URL, DB_USER, this.password);
            } catch (SQLException e) {
                throw new IllegalStateException("Failed to obtain database connection", e);
            }
        };
    }

    // Test constructor (inject mocked connection)
    public UserService(Supplier<Connection> connectionSupplier) {
        this.password = System.getenv("DB_PASSWORD");
        this.connectionSupplier = connectionSupplier;
    }

    public void findUser(String username) throws SQLException {
        String query = "SELECT id, name FROM users WHERE name = ?";

        try (Connection conn = connectionSupplier.get();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = connectionSupplier.get();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}