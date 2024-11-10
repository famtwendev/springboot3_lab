package com.famtwen.spring.__Xay_dung_CRUD_QLSV.service;

import com.famtwen.spring.__Xay_dung_CRUD_QLSV.entity.Student;
import com.famtwen.spring.__Xay_dung_CRUD_QLSV.rest.StudentController;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();

    public Student getStudentById(int id);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudentById(int id);
}
