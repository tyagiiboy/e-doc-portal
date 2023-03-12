package com.edoc.backend.entities;

import com.edoc.backend.enums.DocumentType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "certificates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate implements Serializable {

  private static final long serialVersionUID = -6767962525589390810L;

  @Id
  @Column(name = "admission_id")
  private long admissionId;

  @Column(columnDefinition = "text", name = "document_link")
  private String documentLink;

  @Enumerated(EnumType.STRING)
  @Column(name = "document_type")
  private DocumentType documentType;

  // mapping to be done
  @MapsId
  @ManyToOne
  @JoinColumn(name = "admission_no")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Admission admission;
}
