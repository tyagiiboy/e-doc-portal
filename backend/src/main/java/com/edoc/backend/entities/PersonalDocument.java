package com.edoc.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personal_documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocument implements Serializable {

  private static final long serialVersionUID = -3285421130028260561L;

  @Id
  @Column(name = "document_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer documentId;

  @Column(name = "aadharcard_link", columnDefinition = "text")
  private String aadhar;

  @Column(name = "incomecertificate_link", columnDefinition = "text")
  private String incomeCertificate;

  @Column(name = "domicilecertificate_link", columnDefinition = "text")
  private String domicileCertificate;

  @Column(name = "birthcertificate_link", columnDefinition = "text")
  private String birthCertificate;

  @Column(name = "bankpassbook_link", columnDefinition = "text")
  private String bankPassbook;

  @Column(name = "photo_link", columnDefinition = "text")
  private String photo;

  // mapping to be done
  @OneToOne
  @JoinColumn(name = "owner_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User user;
}
