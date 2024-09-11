package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;

public interface IUserDao {

    User insertUser(User user) throws UserDAOException;
    // User updateUser(User user) throws UserDAOException;
    // User deleteUser(User user) throws UserDAOException;
    // List<User>  getAll()
    User getByUsername(String username) throws UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;

}
