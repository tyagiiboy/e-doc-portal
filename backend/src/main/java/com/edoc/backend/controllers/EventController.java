package com.edoc.backend.controllers;

import com.edoc.backend.entities.Event;
import com.edoc.backend.services.EventService;
import com.edoc.backend.services.ParticipationService;
import com.edoc.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired private EventService eventService;
  @Autowired private SchoolService schoolService;

  @Autowired private ParticipationService participationService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllEvents() {
    return ResponseEntity.ok(eventService.getEvents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getEventDetails(@PathVariable long id) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteEvent(@PathVariable long id) {
    return ResponseEntity.ok(eventService.deleteEventById(id));
  }

  /*
   * This receives an event id and a user id
   * adds an event to the enrolled events list for the
   * particular user.
   */
  @PostMapping("/{eventid}/{userId}")
  public ResponseEntity<?> enrollEvent(@PathVariable Long eventid, @PathVariable Long userId) {
    participationService.addParticipant(eventid, userId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}/participants")
  public ResponseEntity<?> getEnrolledEvents(@PathVariable Long id) {
    return ResponseEntity.ok((participationService.getParticipants(id)));
  }

  @PostMapping("/{eventid}/{userId}")
  public ResponseEntity<?> unEnrollEvent(@PathVariable Long eventid, @PathVariable Long userId) {
    participationService.removeParticipant(eventid, userId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/create/{diseCode}")
  public ResponseEntity<?> creteEvent(@RequestBody Event event, @PathVariable long diseCode) {
    eventService.createEvent(event, diseCode);
    return ResponseEntity.ok().build();
  }

}
