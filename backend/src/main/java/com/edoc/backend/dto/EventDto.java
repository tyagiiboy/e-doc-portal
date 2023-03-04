package com.edoc.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Builder
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
