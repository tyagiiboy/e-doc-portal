package com.edoc.backend.services;

import com.edoc.backend.entities.Admin;
import com.edoc.backend.repositories.AdminRepository;
import com.edoc.backend.repositories.EventRepository;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
