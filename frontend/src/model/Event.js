
export default class Event {
  constructor(
    eventId=null,
    eventName=null,
    description=null,
    startDate=null,
    endDate=null,
    lastDateOfEnrollment=null,
    announcementDate=null
  ) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.description = description;
    this.startDate = startDate;
    this.endDate=endDate;
    this.lastDateOfEnrollment=lastDateOfEnrollment;
    this.announcementDate=announcementDate;
  }
}

