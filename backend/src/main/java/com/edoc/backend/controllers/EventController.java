package com.edoc.backend.controllers;

import com.edoc.backend.dto.ApiResponse;
import com.edoc.backend.entities.Event;
import com.edoc.backend.services.EventService;
import com.edoc.backend.services.ParticipationService;
import com.edoc.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/event")
@SuppressWarnings("unused")
public class EventController {

  @Autowired
  private EventService eventService;

  @Autowired
  private ParticipationService participationService;

  @Autowired
  private StudentService studentService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllEvents() {
    return ResponseEntity.ok(eventService.getEvents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getEventDetailsById(@PathVariable long id) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteEvent(@PathVariable long id) {
    return ResponseEntity.ok(
        ApiResponse.builder()
            .message("Event deleted: " + eventService.deleteEventById(id).getEventName())
            .build()
    );
  }

  @PostMapping("/create/{diseCode}")
  public ResponseEntity<?> creteEvent(@RequestBody Event event, @PathVariable long diseCode) {
    return ResponseEntity.ok(eventService.createEvent(event, diseCode));
  }

  @GetMapping("/school/{diseCode}")
  public ResponseEntity<?> getEventBySchool(@PathVariable long diseCode) {
    return ResponseEntity.ok(eventService.getBySchool(diseCode));
  }

  @GetMapping("/participants/{eventId}")
  public ResponseEntity<?> getParticipants(@PathVariable Long eventId) {
    return ResponseEntity.ok((eventService.getParticipants(eventId)));
  }

  @PostMapping("/image/{eventId}")
  public ResponseEntity<?> uploadEventImage(
      @PathVariable long eventId,
      @RequestParam MultipartFile image
  ) throws IOException {
    eventService.uploadEventImage(eventId, image);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/getImage/{eventId}", produces = { MediaType.IMAGE_GIF_VALUE,
      MediaType.IMAGE_JPEG_VALUE,
      MediaType.IMAGE_PNG_VALUE })
  public ResponseEntity<?> downloadEventImage(@PathVariable Long eventId) throws IOException {
    return ResponseEntity.ok(eventService.downloadEventImage(eventId));
  }

}
