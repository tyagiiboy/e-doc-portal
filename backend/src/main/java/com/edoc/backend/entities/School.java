package com.edoc.backend.entities;

import com.edoc.backend.enums.Level;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
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

  @Column(name = "co_Ed")
  private Boolean coEd;


  @Column(name = "contact_no1")
  private Long contact1;


  @Column(name = "contact_no2")
  private Long contact2;

  @Column(name = "website_link", columnDefinition = "text")
  private String websiteLink;

  @Column(columnDefinition = "tinyint(1) default 1")
  private Boolean authorizationStatus;

  @Column(name = "register_date")
  @Temporal(TemporalType.DATE)
  private Date registerDate;

  @Length(min = 5, max = 20)
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Length(min = 8, max = 20)
  @ToString.Exclude
  private String password;

  /*
   * Mappings --> jpa
   */
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "school")
  private Set<User> userList;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "organizer")
  private Set<Event> eventList;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "school")
  private Set<Admission> admissionList;

  @OneToMany(mappedBy = "respondant", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Transfer> pendingTransfers;

}
