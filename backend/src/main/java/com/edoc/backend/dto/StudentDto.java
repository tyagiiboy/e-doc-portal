package com.edoc.backend.dto;

import com.edoc.backend.enums.Role;
import com.edoc.backend.enums.SchoolClass;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;

    private String email;

    private Role role;

    private String username;

    private Long disecode;

    private SchoolClass schoolClass;

}
