package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}