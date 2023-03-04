package com.edoc.backend.controllers;

import com.edoc.backend.services.EventService;
import com.edoc.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

}
