package com.edoc.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	private List<Admission> participants;

	@ManyToOne
	@JoinColumn(name = "school_id")
	private School organizer;

}
