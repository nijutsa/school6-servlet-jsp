package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDao;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users/register")
public class RegisterController extends HttpServlet {

    private final IUserDao userDao = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserInsertDTO userInsertDTO;

        // Data binding

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");
        String role = request.getParameter("role");

        String errorMessage;
        Map<String, String> errors;

        String usernameMessage;
        String passwordMessage;
        String confirmedPasswordMessage;

        User user;

        try {

            userInsertDTO = new UserInsertDTO(username, password, confirmedPassword, role);

            errors = UserValidator.validate(userInsertDTO);

            if (!errors.isEmpty()) {
                usernameMessage = errors.getOrDefault("username", "");
                passwordMessage = errors.getOrDefault("password", "");
                confirmedPasswordMessage = errors.getOrDefault("confirmedPassword", "");

                request.setAttribute("usernameMessage", usernameMessage);
                request.setAttribute("passwordMessage", passwordMessage);
                request.setAttribute("confirmedPasswordMessage", confirmedPasswordMessage);

                request.setAttribute("userRegisterDTO", userInsertDTO);
                request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);

                return;

            }

            user = userService.insertUser(userInsertDTO);
            UserReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(user);

            request.setAttribute("userInfo", readOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/user-registered.jsp")
                    .forward(request, response);


        } catch (UserDAOException e) {
           errorMessage = e.getMessage();
           request.setAttribute("errorMessage", errorMessage);
           request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp")
                   .forward(request, response);

        }
    }

    private UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getUserId(),user.getUsername(), user.getPassword());
    }
}
