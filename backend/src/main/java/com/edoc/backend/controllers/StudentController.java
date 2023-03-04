package com.edoc.backend.controllers;

import com.edoc.backend.entities.User;
import com.edoc.backend.services.EventService;
import com.edoc.backend.services.SchoolService;
import com.edoc.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }







}
