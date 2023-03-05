package com.edoc.backend.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "admins")
@Data
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @Length(max = 20, min = 5)
    @NotBlank(message = "username cannot be blank")
    private String username;
    @Length(max = 20, min = 8)
    @ToString.Exclude
    private String password;
}
