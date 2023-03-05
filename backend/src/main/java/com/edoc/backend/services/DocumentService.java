package com.edoc.backend.services;

import com.edoc.backend.entities.PersonalDocument;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.CertificateRepository;
import com.edoc.backend.repositories.PersonalDocumentRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DocumentService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PersonalDocumentRepository personalDocumentRepository;

  @Autowired
  private CertificateRepository certificateRepository;
  public PersonalDocument getDocuments(long id){
          User user = userRepository.findById(id).orElseThrow();
          return  personalDocumentRepository.findByUser(user);
  }


}
