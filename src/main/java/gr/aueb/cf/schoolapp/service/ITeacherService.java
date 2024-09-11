package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.FiltersDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO teacher) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO teacher) throws TeacherDAOException, TeacherNotFoundException;

    void deleteTeacher(Integer id) throws TeacherNotFoundException, TeacherDAOException;

    Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    List<Teacher> getFilteredTeachers(FiltersDTO filters) throws TeacherDAOException, TeacherNotFoundException;

}
