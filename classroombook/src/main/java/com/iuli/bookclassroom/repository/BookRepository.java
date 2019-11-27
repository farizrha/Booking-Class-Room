package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Book;
import com.iuli.bookclassroom.models.Event;
import com.iuli.bookclassroom.models.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


}