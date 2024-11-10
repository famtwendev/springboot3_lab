package com.famtwen.spring.__Xay_dung_CRUD_QLSV.dao;

import com.famtwen.spring.__Xay_dung_CRUD_QLSV.entity.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> findAll();

    public Student getById(int id);

    public Student save(Student student);

    public Student saveAndFlush(Student student);

    public void deleteById(int id);
}
