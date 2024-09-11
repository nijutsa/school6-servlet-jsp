package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDao;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserLogInDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

public class AuthenticationProvider {
    private final  static IUserDao userDAO = new UserDAOImpl();
    private final  static IUserService userService = new UserServiceImpl(userDAO);

    public static boolean authenticateUser(UserLogInDTO userLogInDTO) throws UserDAOException {
        return userService.isUserValid(userLogInDTO.getUsername(), userLogInDTO.getPassword());
    }
}
