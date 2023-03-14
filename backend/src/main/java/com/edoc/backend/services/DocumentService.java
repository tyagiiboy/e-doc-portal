package com.edoc.backend.services;

import com.edoc.backend.entities.PersonalDocument;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.CertificateRepository;
import com.edoc.backend.repositories.PersonalDocumentRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class DocumentService {

  @Autowired
  private ImageHandlingService imageHandlingService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PersonalDocumentRepository personalDocumentRepository;

  public void uploadDocumentsToUser(Long id, MultipartFile[] documents) throws IOException {
    User user = userRepository.findById(id).orElseThrow();
    String[] paths = imageHandlingService.uploadImages(id, documents);
    PersonalDocument personalDocument = PersonalDocument.builder()
        .aadhar(paths[0])
        .bankPassbook(paths[1])
        .birthCertificate(paths[2])
        .domicileCertificate(paths[3])
        .incomeCertificate(paths[4])
        .photo(paths[5])
        .user(user)
        .build();
    personalDocumentRepository.save(personalDocument);
  }

  public byte[] serveDocument(Long id, String name) throws IOException {
    PersonalDocument personalDocument = personalDocumentRepository.findByUserId(id).orElseThrow();
    byte[] image = null;
    switch (name) {
      case "aadhar":
        image = imageHandlingService.serveImage(personalDocument.getAadhar());
        break;
      case "bankPassbook":
        image = imageHandlingService.serveImage(personalDocument.getBankPassbook());
        break;
      case "birthCertificate":
        image = imageHandlingService.serveImage(personalDocument.getBirthCertificate());
        break;
      case "domicileCertificate":
        image = imageHandlingService.serveImage(personalDocument.getDomicileCertificate());
        break;
      case "incomeCertificate":
        image = imageHandlingService.serveImage(personalDocument.getIncomeCertificate());
        break;
      case "photo":
        image = imageHandlingService.serveImage(personalDocument.getPhoto());
        break;
    }
    return image;
  }

}
