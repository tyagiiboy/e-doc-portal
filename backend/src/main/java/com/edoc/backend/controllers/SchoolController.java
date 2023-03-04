package com.edoc.backend.controllers;

import com.edoc.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {
  @Autowired private SchoolService schoolService;

  @GetMapping("/all")
  public ResponseEntity<?> getSchools() {
    return ResponseEntity.ok(schoolService.getSchools());
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<?> getUnauthorizedSchools(@PathVariable boolean status) {
    return ResponseEntity.ok(schoolService.getSchoolsByAuthorizationStatus(status));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> schoolDetailsByDiseCode(@PathVariable long id) {
    return ResponseEntity.ok(schoolService.getSchoolByDiseCode(id));
  }
}
