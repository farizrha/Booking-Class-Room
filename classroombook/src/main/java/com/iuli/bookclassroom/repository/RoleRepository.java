package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}