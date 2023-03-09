package com.edoc.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

    @JsonProperty(access = Access.WRITE_ONLY)
//    @Length(max = 20, min = 5)
    @Column(length = 500)
    @NotBlank(message = "username cannot be blank")
    private String username;
    @Length(min = 8)
    @ToString.Exclude
    private String password;
}
