package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}