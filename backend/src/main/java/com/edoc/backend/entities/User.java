package com.edoc.backend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.edoc.backend.enums.Category;
import com.edoc.backend.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -6829875523246984650L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name="first_name")
	@NotBlank(message="Name can not be Empty")
	@Length(max = 20, message = "Invalid first name!!!!!!")
	private String firstName;
	
	@Column(name="last_name", unique = true)
	@Length(max = 20, message = "Invalid last name!!!!!!")
	private String lastName;
	
//	@NotBlank(message="Gender can not be Empty")
	private Character gender;
	
//	@NotBlank(message="Date of Birth can not be Empty")
	@Column(name="date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
//	@Length(min = 12, max = 12, message = "Invalid Aadhar Number!!!!!!")
//	@NotBlank(message="AadharNumber can not be Empty")
	private Long aadharNumber;
	
	@Email(message="invalid email format!!!!!!!!")
	private String email;
	
//	@NotBlank(message="Date of Joining can not be Empty")
	@Column(name="date_of_registration")
	@CreationTimestamp
	private Date dateOfRegistration;
	
	@Column(name="user_name")
	private String username;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@Length(min = 8, max = 20)
	private String password;
	
	@Column(name="father_name")
	@NotBlank(message="Name can not be Empty")
	@Length(max = 20)
	private String fastherName;
	
	@Column(name="mother_name")
	@NotBlank(message="Name can not be Empty")
	@Length(max = 20)
	private String motherName;
	
//	@NotBlank(message="Category can not be Empty")
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Column(name="account_number")
//	@Length(min=8,max = 15, message = "Invalid Account Number!!!!!!")
	private Long accoutNumber;
	
	@Length(min=10, max=100)
	private String address;
	
//	@Length(min=10, max=10, message="Invalid Contact Number!!!!!!!")
	@Column(name="contact_no")
	private Long contactNo;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	/*
	 * mappings
	 */
	@ManyToOne
	@JoinColumn(name="school_dise_code")
	private School school;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private PersonalDocument presonalDocument;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Admission> admissionList;

	@OneToOne(mappedBy = "user")
	private Transfer transferRequest;
	
}
