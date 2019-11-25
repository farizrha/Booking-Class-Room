package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Lecturer;
import com.iuli.bookclassroom.models.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}