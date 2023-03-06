package com.edoc.backend.controllers;

import com.edoc.backend.dto.TransferDto;
import com.edoc.backend.dto.TransferRequest;
import com.edoc.backend.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@SuppressWarnings("unused")
public class TransferController {

  @Autowired
  private TransferService transferService;

  @PostMapping("/create")
  public ResponseEntity<?> createTransfer(@RequestBody TransferRequest transferRequest) {
    if (transferService.createTransferOfUserIdToDiseCode(transferRequest) == null)
      return ResponseEntity.status(HttpStatus.valueOf(500)).build();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/all/{diseCode}")
  public ResponseEntity<?> getTransfersForSchool(@PathVariable long diseCode) {
    List<TransferDto> transferDtos = transferService.getAllTransferRequestForSchoolByDiseCode(diseCode);
    if (transferDtos == null) return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(transferDtos);
  }

  @PostMapping("/accept/{diseCode}")
  public ResponseEntity<?> acceptTransfersForSchool(
      @RequestBody List<Long> transfers,
      @PathVariable long diseCode
  ) {
    transferService.acceptListedTransfersForSchoolDiseCode(transfers, diseCode);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/reject/{diseCode}")
  public ResponseEntity<?> rejectTransfersForSchool(
      @RequestBody List<Long> transfers,
      @PathVariable Long diseCode
  ) {
    transferService.rejectListedTransfersForSchoolDiseCode(transfers, diseCode);
    return ResponseEntity.ok().build();
  }

}


































