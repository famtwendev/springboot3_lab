package com.famtwen.spring.__Xay_dung_CRUD_QLSV.dao;

import com.famtwen.spring.__Xay_dung_CRUD_QLSV.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
