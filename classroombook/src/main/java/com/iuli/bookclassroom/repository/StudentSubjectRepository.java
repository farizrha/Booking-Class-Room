package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.StudentSubject;
import com.iuli.bookclassroom.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

    List<StudentSubject> findAllBySubject(Subject subject);
}