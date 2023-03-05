package com.edoc.backend.controllers;

import com.edoc.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
