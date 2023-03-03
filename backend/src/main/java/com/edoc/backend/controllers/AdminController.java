package com.edoc.backend.controllers;

import com.edoc.backend.dto.UserDto;
import com.edoc.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SchoolService adminService;

    @GetMapping("/profile/{id}")
    public @ResponseBody UserDto getProfileById(@PathVariable long id) {
        return adminService.retrieveUserById(id);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(adminService.countOfSchools());
    }

    @GetMapping("/schools")
    public ResponseEntity<?> getSchoolList(){
        return ResponseEntity.ok(adminService.getSchoolList());
    }

}
