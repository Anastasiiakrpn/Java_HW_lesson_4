package org.anastasiiapanchenko.lesson4.repository;

import org.anastasiiapanchenko.lesson4.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/java_database";
    private static final String USER = "kit";
    private static final String PASSWORD = "kit111";


    private static final int EMAIL_INDEX = 1;
    private static final int PHONE_INDEX = 2;
    private static final int PASSWORD_INDEX = 3;

    @Override
    public User save(User user) {
        String sqlReq = "INSERT INTO users (email, phone, password) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlReq, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(EMAIL_INDEX, user.getEmail());
            preparedStatement.setString(PHONE_INDEX, user.getPhoneNumber());
            preparedStatement.setString(PASSWORD_INDEX, user.getPassword());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error: Failed, no rows were updated");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Error: Failed, ID not received");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while saving user", e);
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sqlReq = "SELECT id, email, phone, password FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement prStatement = conn.prepareStatement(sqlReq)) {

            prStatement.setLong(1, id);
            ResultSet result = prStatement.executeQuery();

            if (result.next()) {
                User user = new User(
                        result.getLong("id"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("password")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while finding user by ID", e);
        }
        return Optional.empty();
    }
}
