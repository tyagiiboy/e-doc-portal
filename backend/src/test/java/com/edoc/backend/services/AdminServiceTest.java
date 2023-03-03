package com.edoc.backend.services;

import com.edoc.backend.entities.User;
import com.edoc.backend.dto.UserDto;
import com.edoc.backend.enums.Category;
import com.edoc.backend.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class AdminServiceTest {
    @Autowired
    AdminService service;

    @Test
    public void addUserTest() {
        User user = User.builder()
                .aadharNumber(Long.parseLong("123412341234"))
                .accoutNumber(Long.parseLong("123412341234"))
                .address("SB Road, Pune, India")
                .category(Category.GEN)
                .contactNo(Long.parseLong("1234123423"))
                .dateOfBirth(Date.valueOf("1990-12-12"))
                .email("admin@email.com")
                .fastherName("Father Name")
                .firstName("Admin")
                .lastName("Name")
                .motherName("Mother Name")
                .gender('M')
                .username("username")
                .password("password")
                .role(Role.ADMIN)
                .build();
        UserDto userDto = service.createAdmin(user);
        System.out.println(userDto);
    }
}