package com.edoc.backend.controllers;

import com.edoc.backend.repositories.EventRepository;
import com.edoc.backend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("/{eventid}/{id}")
  public ResponseEntity<?> enrollEvent(@PathVariable Long eventid,@PathVariable Long id){
    eventService.addParticipant(eventid,id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getEnrolledEvents(@PathVariable Long id){
    return ResponseEntity.ok((eventService.getParticipants(id)));
  }

  @PostMapping("/{eventid}/{id}")
  public ResponseEntity<?> unenrollEvent(@PathVariable Long eventid,@PathVariable Long id){
    eventService.addParticipant(eventid,id);
    return ResponseEntity.ok().build();
  }


}
