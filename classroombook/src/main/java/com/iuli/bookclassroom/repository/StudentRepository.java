package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}