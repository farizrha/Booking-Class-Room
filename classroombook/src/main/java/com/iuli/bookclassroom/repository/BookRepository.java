package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Book;
import com.iuli.bookclassroom.models.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}