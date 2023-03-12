package com.edoc.backend.controllers;

import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@SuppressWarnings("unused")
public class SchoolController {

  @Autowired
  private SchoolService schoolService;

  @GetMapping("/all")
  public ResponseEntity<?> getSchools() {
    return ResponseEntity.ok(schoolService.getSchoolList());
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<?> schoolsByStatus(@PathVariable boolean status) {
    return ResponseEntity.ok(schoolService.getSchoolListByAuthorizationStatus(status));
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<?> schoolDetailsByDiseCode(@PathVariable long id) {
    return ResponseEntity.ok(schoolService.getSchoolByDiseCode(id));
  }

  @PostMapping("/set/status/{status}")
  @Modifying
  public ResponseEntity<?> setAuthorizations(
      @RequestBody List<SchoolDto> schools,
      @PathVariable boolean status
  ) {
    if (schoolService.setAuthorizationOfSchoolsInList(schools, status) != null)
      return ResponseEntity.accepted().build();
    return ResponseEntity.status(HttpStatus.valueOf(500)).build();
  }

  @PostMapping("/remove/student")
  public ResponseEntity<?> removeFromSchool(@RequestBody List<StudentDto> students) {
    if (schoolService.removeStudentsInList(students)) return ResponseEntity.ok().build();
    return ResponseEntity.badRequest().build();
  }

}
