package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/teachers/update")
public class TeacherUpdateController extends HttpServlet {

    public final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    public final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstname, lastname);
        request.setAttribute("updateDTO", updateDTO);

        request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstname, lastname);
        Map<String, String> errors;
        String firstnameMessage;
        String lastnameMessage;
        String errorMessage;
        Teacher teacher;

        try {
            // Validate dto
            errors = TeacherValidator.validate(updateDTO);

            if (!errors.isEmpty()) {
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");

                request.setAttribute("firstnameMessage", firstnameMessage);
                request.setAttribute("lastnameMessage", lastnameMessage);
                request.setAttribute("updateDTO", updateDTO);
                request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp")
                        .forward(request, response);
                return;
            }

            // Call the service
            teacher = teacherService.updateTeacher(updateDTO);
            TeacherReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(teacher);
            request.setAttribute("teacherInfo", readOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-updated.jsp")
                    .forward(request, response);
        } catch (TeacherNotFoundException | TeacherDAOException e) {
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp")
                    .forward(request, response);
        }
    }

    private TeacherReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
    }
}
