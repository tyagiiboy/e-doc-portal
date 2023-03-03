package com.edoc.backend.services;

import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.entities.School;
import com.edoc.backend.enums.Level;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchoolServiceTest {

    @Autowired
    private SchoolService service;

    @Test
    public void createSchool() {
        School school = School.builder()
                .diseCode(23090704872l)
                .address("Sambhaji Nagar Road, Pune, India")
                .coEd(true)
                .level(Level.HigherSecondary)
                .name("Model School")
                .affiliation("State Board")
                .authorizationStatus(true)
                .establishedDate(Date.valueOf(LocalDate.now()))
                .websiteLink("https://modelschool.com")
                .build();
        SchoolDto s = service.createSchool(school);
        assertTrue(s != null);
        System.out.println(s);
    }

}