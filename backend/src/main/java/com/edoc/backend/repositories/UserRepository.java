package com.edoc.backend.repositories;

import com.edoc.backend.entities.User;
import com.edoc.backend.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
}
