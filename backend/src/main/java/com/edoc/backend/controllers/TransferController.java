package com.edoc.backend.controllers;

import com.edoc.backend.dto.TransferDto;
import com.edoc.backend.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

  @Autowired
  private TransferService transferService;

  @PostMapping("/create/{userId}/{diseCode}")
  public ResponseEntity<?> createTransfer(
      @PathVariable long userId,
      @PathVariable long diseCode
  ) {
    transferService.createTransfer(userId, diseCode);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/all/{diseCode}")
  public ResponseEntity<?> getTransfersForSchool(@PathVariable long diseCode) {
    List<TransferDto> transferDtos = transferService.getAllByDiseCode(diseCode);
    if (transferDtos == null) return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(transferDtos);
  }

  @PostMapping("/accept/{diseCode}")
  public ResponseEntity<?> acceptTransfers(
      @RequestBody List<Long> transfers,
      @PathVariable long diseCode
  ) {
    transferService.acceptListedTransfersBySchool(transfers, diseCode);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/reject/{diseCode}")
  public ResponseEntity<?> rejectTransfers(
      @RequestBody List<Long> transfers,
      @PathVariable Long diseCode
  ) {
    transferService.rejectListedTransfersBySchool(transfers, diseCode);
    return ResponseEntity.ok().build();
  }

}


































