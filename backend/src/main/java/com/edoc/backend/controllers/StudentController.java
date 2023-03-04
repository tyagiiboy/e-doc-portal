package com.edoc.backend.controllers;

import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.User;
import com.edoc.backend.services.EventService;
import com.edoc.backend.services.SchoolService;
import com.edoc.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

//    @PutMapping("/remove/school")
//    public ResponseEntity<?> removeFromSchool(@RequestBody List<StudentDto> students) {
//        if ()
//        return null;
//    }

}
