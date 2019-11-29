package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Event;
import com.iuli.bookclassroom.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByType(Event event);
/*
    @Query(value = "SELECT type FROM Event", nativeQuery = true)
    List<Event> findByType(Event type);
*/
}