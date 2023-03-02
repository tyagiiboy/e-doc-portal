package com.edoc.backend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="personal_documents")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocument implements Serializable {
	
	private static final long serialVersionUID = -3285421130028260561L;

	@Id	
	@Column(name = "document_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int documentId;
	
	@Column(name="aadharcard_link", columnDefinition = "text")
	private String aadharNummber;
	
	@Column(name="incomecertificate_link", columnDefinition = "text")
	private String incomeCertificate;
	
	@Column(name="domicilecertificate_link", columnDefinition = "text")
	private String domicileCertificate;
	
	@Column(name="birthcertificate_link", columnDefinition = "text")
	private String birthCertificate;
	
	@Column(name="bankpassbook_link", columnDefinition = "text")
	private String bankPassbook;
	
	@Column(name="photo_link", columnDefinition = "text")
	private String photo;
	
	// mapping to be done
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
}
