package com.edoc.backend.dto;

import com.edoc.backend.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthRequest {
  Long id;
  String username;
  String password;
  Role role;
}
