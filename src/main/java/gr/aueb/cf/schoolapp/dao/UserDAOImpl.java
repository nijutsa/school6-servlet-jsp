package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDao{
    @Override
    public User insertUser(User user) throws UserDAOException {
        String sql = "INSERT INTO users (username, password) VALUES(?,?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            // extract model info
            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, SecUtil.hashPassword(password));

            ps.executeUpdate();
            //logging
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("User already exists.Enter another username");
        }

    }

    @Override
    public User getByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;
    //  List<User> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password") );
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error in user");
        }

    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String sql = "SELECT * FROM users WHERE username = ? ";
        User user;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password") );
            } else {
                return false;
            }
            return SecUtil.isPasswordValid(password, user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error in user");
        }

    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        String sql = "SELECT count(*) FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;
        int count = 0;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.getInt(1);
            }
            return count > 0;
             } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("SQL error for user with username:" + username);
        }


    }
}
