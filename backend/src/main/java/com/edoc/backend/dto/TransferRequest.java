package com.edoc.backend.dto;

import com.edoc.backend.enums.SchoolClass;
import com.edoc.backend.enums.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
  long userId;
  long diseCode;
  SchoolClass schoolClass;
  Stream stream;
}
