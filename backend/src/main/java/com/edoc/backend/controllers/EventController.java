package com.edoc.backend.controllers;

import com.edoc.backend.repositories.EventRepository;
import com.edoc.backend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired
  EventService eventService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllEvents() {
    return ResponseEntity.ok(eventService.getEvents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getEventDetails(@PathVariable long id) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }

  @GetMapping("/revoke/{id}")
  public ResponseEntity<?> revokeEvent(@PathVariable long id) {
    return ResponseEntity.ok(eventService.deleteEventById(id));
  }

}
