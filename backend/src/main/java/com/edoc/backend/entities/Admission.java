package com.edoc.backend.entities;

import com.edoc.backend.enums.SchoolClass;
import com.edoc.backend.enums.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	private School school;

	@ManyToMany
//	@JoinTable(name = "participations",joinColumns = @JoinColumn(name="admission_id"),
//	inverseJoinColumns = @JoinColumn(name="event_id"))
	@JoinTable(
			name = "participations",
			joinColumns = {@JoinColumn(name = "admission_id") , @JoinColumn(name = "event_id")}
	)
	private Set<Event> participations = new HashSet<>();
	
	@OneToMany(mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Certificate> certificates = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public int compareTo(Admission o) {
		return o.getAdmissionId().compareTo(admissionId);
	}
}
