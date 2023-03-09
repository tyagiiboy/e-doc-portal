package com.edoc.backend.repositories;

import com.edoc.backend.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
  List<School> findByAuthorizationStatus(boolean status);

  Optional<School> findByUsername(String username);
}
