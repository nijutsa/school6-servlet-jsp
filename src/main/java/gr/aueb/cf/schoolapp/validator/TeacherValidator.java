package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dto.BaseDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {

    private final static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final static ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    public TeacherValidator() {
    }

    public static <T extends BaseDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<String, String>();


        if (dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "Firstname should be between 2 and 32 characters");
        }

        if (dto.getLastname().length() < 2 || dto.getLastname().length() > 32) {
            errors.put("lastname", "Lastname should be between 2 and 32 characters");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Firstname should not include whitespaces");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "lastname should not include whitespaces");
        }

        if (dto.getFirstname().matches("^[a-zA-Z]+$")) {
            errors.put("firstname", "Firstname should only  include letters");
        }

        if (dto.getLastname().matches("^[a-zA-Z]+$")) {
            errors.put("lastname", "Firstname should only  include letters");
        }

        return errors;
    }
}
