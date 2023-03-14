package pl.coderslab.dao;


import pl.coderslab.entity.User;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao  {


    public User create(User user) {
        try (Connection connection = DbUtil.getConnection();) {
            String sqlQuery = "INSERT INTO user (id, name, email, password) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());


            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public User read(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            User user;
            String sqlQuery = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password");

                user = new User(userId, userName, userEmail, userPassword);
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void update(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            String sqlQuery = "UPDATE  user SET name = ?, email = ?, password = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            String sqlQuery = "DELETE FROM user WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<User> findAll() {
        try (Connection connection = DbUtil.getConnection()) {

            List<User> userList = new ArrayList<>();
            User user;
            String sqlQuery = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password");


                user = new User(userId, userName, userEmail, userPassword);
               userList.add(user);

            }

            preparedStatement.close();
            return userList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void removeALL() {

        try (Connection connection = DbUtil.getConnection()) {
            String sqlQuery = "DELETE FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
