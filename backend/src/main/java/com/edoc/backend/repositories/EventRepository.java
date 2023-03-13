package com.edoc.backend.repositories;

import com.edoc.backend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
  List<Event> findByOrganizerDiseCode(long diseCode);
}
