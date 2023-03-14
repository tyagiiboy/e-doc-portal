package com.edoc.backend.controllers;

import com.edoc.backend.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@SuppressWarnings("unused")
public class DocumentController {

  @Autowired
  private DocumentService documentService;

  @PostMapping("/upload/{id}")
  public ResponseEntity<?> uploadDocuments(
      @PathVariable Long id,
      @RequestParam MultipartFile[] documents) throws IOException {
    documentService.uploadDocumentsToUser(id, documents);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/download/{userId}/{name}", produces = {
      MediaType.IMAGE_GIF_VALUE,
      MediaType.IMAGE_JPEG_VALUE,
      MediaType.IMAGE_PNG_VALUE
  })
  public ResponseEntity<?> downloadDocument(
      @PathVariable Long userId,
      @PathVariable String name
  ) throws IOException {
    return ResponseEntity.ok(documentService.serveDocument(userId, name));
  }

}
