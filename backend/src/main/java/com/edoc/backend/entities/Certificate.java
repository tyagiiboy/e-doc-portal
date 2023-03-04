package com.edoc.backend.entities;

import com.edoc.backend.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(columnDefinition = "text", name = "document_link")
  private String documentLink;

  @Enumerated(EnumType.STRING)
  @Column(name = "document_type")
  private DocumentType documentType;

  // mapping to be done
  @Id
  @ManyToOne
  @JoinColumn(name = "admission_no")
  private Admission admission;
}
