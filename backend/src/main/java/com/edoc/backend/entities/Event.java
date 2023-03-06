package com.edoc.backend.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

	private static final long serialVersionUID = -4622588316227796504L;

	@Id
	@Column(name = "event_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long eventId;

	@Column(name = "event_name")
	private String eventName;

	@Column(columnDefinition = "text")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(name = "last_date_of_enrollment")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastDateOfEnrollment;

	@Column(name = "announcement_date")
	@CreationTimestamp
	private Date announcementDate;

	@ManyToMany(mappedBy = "participations")
	@EqualsAndHashCode.Exclude
	private Set<Admission> participants = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "school_id")
	@EqualsAndHashCode.Exclude
	private School organizer;

}
