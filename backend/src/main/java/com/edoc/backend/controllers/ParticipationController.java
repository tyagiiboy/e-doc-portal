package com.edoc.backend.controllers;

import com.edoc.backend.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController("/participation")
@Transactional
@SuppressWarnings("unused")
public class ParticipationController {

  @Autowired private ParticipationService participationService;

  @PostMapping("/add/{eventid}/{userId}")
  public ResponseEntity<?> participate(@PathVariable Long eventid, @PathVariable Long userId) {
    participationService.addParticipationOfUserIdToEventId(eventid, userId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/remove/{eventid}/{userId}")
  public ResponseEntity<?> revokeParticipation(@PathVariable Long eventid, @PathVariable Long userId) {
    participationService.removeParticipationOfUserIdFromEventId(eventid, userId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/all/{userId}")
  public ResponseEntity<?> getAllParticipations(@PathVariable long id) {
    return ResponseEntity.ok(participationService.getAllParticipationsOfUserId(id));
  }

}
