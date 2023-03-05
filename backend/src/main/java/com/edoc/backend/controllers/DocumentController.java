package com.edoc.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
@SuppressWarnings("unused")
public class DocumentController {

  public ResponseEntity<?> index() {
    return ResponseEntity.ok().build();
  }

}
