package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


@WebServlet("/teachers/insert")
public class TeachersInsertController extends HttpServlet {

    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TeacherInsertDTO teacherInsertDTO;

        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();

        String errormessage;
        Map<String, String> errors;

        String firstnameMessage;
        String lastnameMessage;

        Teacher teacher;

        try {

            teacherInsertDTO = new TeacherInsertDTO(firstname, lastname);


            errors = TeacherValidator.validate(teacherInsertDTO);

            if (!errors.isEmpty()) {
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");

                request.setAttribute("firstnameMessage", firstnameMessage);
                request.setAttribute("lastnameMessage", lastnameMessage);

                request.setAttribute("teacherInsertDTO", teacherInsertDTO);
                request.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(request, response);

                return;

            }

            teacher = teacherService.insertTeacher(teacherInsertDTO);
            TeacherReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(teacher);

            request.setAttribute("teacherReadOnlyDTO", readOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-registered.jsp").forward(request, response);



        } catch (TeacherDAOException e) {
            errormessage = e.getMessage();
            request.setAttribute("errorMessage", errormessage);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(request, response);
        }
    }

    private TeacherReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
    }
}
