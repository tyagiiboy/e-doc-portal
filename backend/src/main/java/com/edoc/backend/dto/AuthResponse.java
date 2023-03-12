package com.edoc.backend.dto;

import com.edoc.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
  private Long id;
  private String username;
  private Role role;
  private String token;
}
