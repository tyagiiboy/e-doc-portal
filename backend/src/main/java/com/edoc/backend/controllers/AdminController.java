package com.edoc.backend.controllers;

import com.edoc.backend.dto.ApiResponse;
import com.edoc.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@SuppressWarnings("unused")
public class AdminController {

    @Autowired
    private SchoolService adminService;
    @Autowired SchoolService schoolService;

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(
            ApiResponse.builder()
                .message("Total: " + schoolService.countOfSchools())
                .build()
        );
    }

}
