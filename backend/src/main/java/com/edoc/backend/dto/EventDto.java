package com.edoc.backend.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long eventId;
    private String eventName;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date lastDateOfEnrollment;
    private Date announcementDate;
    private SchoolDto organizer;
}
