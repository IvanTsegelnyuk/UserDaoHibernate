package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NULL, " +
                "lastName VARCHAR(45) NULL, " +
                "age TINYINT NULL, " +
                "PRIMARY KEY (id), " +
                "UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
            connection.commit();
            System.out.println("Таблица users создана");
        } catch (SQLException e) {
            commonExceptionBlock(e);
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
            connection.commit();
            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            commonExceptionBlock(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            commonExceptionBlock(e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int count = preparedStatement.executeUpdate();
            connection.commit();
            if (count == 1) System.out.println("User с id " + id + " удален из базы данных");
            else System.out.println("Пользователя с id " + id + " нет в базе данных");
        } catch (SQLException e) {
            commonExceptionBlock(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            connection.commit();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                resultList.add(user);
            }
        } catch (SQLException e) {
            commonExceptionBlock(e);
        }
        return resultList;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
            connection.commit();
            System.out.println("Таблица users очищена");
        } catch (SQLException e) {
            commonExceptionBlock(e);
        }
    }

    private void commonExceptionBlock(SQLException e) {
        e.printStackTrace();
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ошибка при выполнении rollback");
        }
    }
}
