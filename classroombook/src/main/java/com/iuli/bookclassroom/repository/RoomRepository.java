package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}