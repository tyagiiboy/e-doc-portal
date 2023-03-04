package com.edoc.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Builder
public class ResponseMessage {

  private String message;
  private Object obj;

}
