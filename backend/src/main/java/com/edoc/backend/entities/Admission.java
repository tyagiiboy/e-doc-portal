package com.edoc.backend.entities;

import com.edoc.backend.enums.SchoolClass;
import com.edoc.backend.enums.Stream;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admissions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(
    exclude = {"school", "user", "certificates", "participations"}
)
public class Admission implements Serializable, Comparable<Admission> {
  private static final long serialVersionUID = -4895496645611865989L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "admissino_id")
  private Long admissionId;

  @CreationTimestamp
  @Column(name = "date_of_admission")
  private LocalDate dateOfAdmission;

  @Enumerated(EnumType.STRING)
  @Column(name = "class")
  private SchoolClass schoolClass;

  @Enumerated(EnumType.STRING)
  private Stream stream;

  @ManyToOne
  @JoinColumn(name = "school_id")
  @ToString.Exclude
  private School school;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "participations", joinColumns = @JoinColumn(name = "admission_id"),
      inverseJoinColumns = @JoinColumn(name = "event_id"))
  @ToString.Exclude
  private Set<Event> participations = new HashSet<>();

  @OneToMany(mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private Set<Certificate> certificates = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private User user;

  @Override
  public int compareTo(Admission o) {
    return o.getAdmissionId().compareTo(admissionId);
  }
}
