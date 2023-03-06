package com.edoc.backend.dto;

import com.edoc.backend.enums.Role;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;

    private String email;

    private Role role;

    private String username;

    private Long diseCode;

    private Long contactNo;

    private String address;

    private Long accountNumber;

}
