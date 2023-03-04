package com.edoc.backend.services;

import com.edoc.backend.dto.EventDto;
import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.entities.Admin;
import com.edoc.backend.entities.School;
import com.edoc.backend.enums.Role;
import com.edoc.backend.repositories.AdminRepository;
import com.edoc.backend.repositories.EventRepository;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;
  @Autowired
  private SchoolRepository schoolRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EventRepository eventRepository;

  public Admin createAdmin(Admin admin) {
    return adminRepository.save(admin);
  }

  public Admin updateAdmin(Admin admin) {
    return adminRepository.save(admin);
  }

}
