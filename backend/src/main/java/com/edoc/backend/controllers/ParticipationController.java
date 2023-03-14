package com.edoc.backend.controllers;

import com.edoc.backend.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participation")
@SuppressWarnings("unused")
public class ParticipationController {

  @Autowired private ParticipationService participationService;

  @PostMapping("/add/{eventid}/{userId}")
  public ResponseEntity<?> participate(@PathVariable Long eventid, @PathVariable Long userId) {
    if(!participationService.addParticipationOfUserIdToEventId(eventid, userId))
      return ResponseEntity.badRequest().build();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/remove/{eventid}/{userId}")
  public ResponseEntity<?> revokeParticipation(@PathVariable Long eventid, @PathVariable Long userId) {
    participationService.removeParticipationOfUserIdFromEventId(eventid, userId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/all/{userId}")
  public ResponseEntity<?> getAllParticipations(@PathVariable long userId) {
    return ResponseEntity.ok(participationService.getAllParticipationsOfUserId(userId));
  }

}
