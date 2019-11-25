package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Event;
import com.iuli.bookclassroom.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}