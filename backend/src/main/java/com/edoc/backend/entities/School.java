package com.edoc.backend.entities;

import com.edoc.backend.enums.Level;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schools")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School implements Serializable {

  private static final long serialVersionUID = -8669416521523033905L;

  @Id
  @Column(name = "dise_code", nullable = false)
//	@Length(min=10, max=15, message="Invalid Dise Code!")
  private Long diseCode;

  @NotBlank(message = "Name can not be Empty")
  @Length(max = 50, message = "Invalid name!")
  private String name;

  @NotBlank(message = "Name can not be Empty")
  @Length(max = 50, message = "Invalid name!")
  private String affiliation;

  @Column(name = "established_date")
  @Temporal(TemporalType.DATE)
  private Date establishedDate;

  @Enumerated(EnumType.STRING)
  private Level level;

  @Length(min = 10, max = 100)
  private String address;

  @Column(columnDefinition = "tinyint(1) default 1")
  @EqualsAndHashCode.Exclude
  private Boolean coEd;

  @Column(name = "contact_no1")
  @EqualsAndHashCode.Exclude
  private Long contact1;

  @Column(name = "contact_no2")
  @EqualsAndHashCode.Exclude
  private Long contact2;

  @Column(name = "website_link", columnDefinition = "text")
  @EqualsAndHashCode.Exclude
  private String websiteLink;

  @Column(columnDefinition = "tinyint(1) default 1")
  @EqualsAndHashCode.Exclude
  private Boolean authorizationStatus;

  @Column(name = "register_date")
  @CreationTimestamp
  @EqualsAndHashCode.Exclude
  private Date registerDate;

  @Length(min = 5, max = 20)
  private String username;

  @Email(message="invalid email format!!!!!!!!")
  @Column(unique = true)
  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//  @Length(min = 8)
  @ToString.Exclude
  @Column(columnDefinition = "text")
  @EqualsAndHashCode.Exclude
  private String password;

  /*
   * Mappings --> jpa
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<User> userList = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "organizer")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Event> eventList = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Admission> admissionList = new HashSet<>();

  @OneToMany(mappedBy = "respondant", cascade = CascadeType.ALL, orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Transfer> pendingTransfers = new HashSet<>();

}
