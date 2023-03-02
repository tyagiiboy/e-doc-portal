package com.edoc.backend.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edoc.backend.enums.SchoolClass;
import com.edoc.backend.enums.Stream;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admissions")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admission implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4895496645611865989L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "admissino_id")
	private Long admissionId;
	
	@CreationTimestamp
	@Column(name = "date_of_admission")
	private LocalDate dateOfAdmission;
	
	@Column(name = "sr_no", nullable = false, updatable = false)
	private Integer srNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "class")
	private SchoolClass schoolClass;
	
	@Enumerated(EnumType.STRING)
	private Stream stream;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@ManyToMany
	@JoinTable(name = "participations",joinColumns = @JoinColumn(name="admission_id"),
	inverseJoinColumns = @JoinColumn(name="event_id"))
	private List<Event> participations;
	
	@OneToMany(mappedBy = "admission", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Certificate> certificates;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
