import {
  ALL_EVENTS_URL,
  GET_EVENT_BY_DISECODE_URL,
  CREATE_EVENT_BY_DISECODE_URL,
  PARTICIPATE_EVENT_URL,
  POST_EVENT_IMAGE_URL,
  GET_EVENT_IMAGE_URL,
  PARTICIPATIONS_OF_USER_URL,
  UNPARTICIPATED_EVENTS_OF_USER_URI
} from "../commons/constants";

import axios from "axios";
import { authHeader, authImageHeader } from "./base.service";

class EventService {
  getAllEvents() {
    return axios.get(ALL_EVENTS_URL, {
      headers: authHeader(),
    })
  }

  getEventsBySchoolId(diseCode) {
    return axios.get(GET_EVENT_BY_DISECODE_URL + diseCode, {
      headers: authHeader()
    })
  }

  createEvent(event, diseCode) {
    return axios.post(CREATE_EVENT_BY_DISECODE_URL + diseCode, event, {
      headers: authHeader()
    })
  }

  participateEvent(eventid, userid) {
    return axios.post(PARTICIPATE_EVENT_URL + eventid + '/' + userid, {
      headers: authHeader()
    })
  }

  getAllParticipationsOfUser(userId) {
    return axios.get(PARTICIPATIONS_OF_USER_URL + userId, {
      headers: authHeader()
    })
  }

  getUnparticipatedEvents(userId) {
    return axios.get(UNPARTICIPATED_EVENTS_OF_USER_URI + userId, {
      headers: authHeader()
    })
  }

  uploadEventImage(eventId, image) {
    let formData = new FormData()
    formData.append('image', image)

    return axios.post(POST_EVENT_IMAGE_URL + eventId, formData, {
      headers: authImageHeader()
    });
  }

  downloadEventImage(eventId) {
    return axios.get(GET_EVENT_IMAGE_URL + eventId, {
      responseType: 'blob',
      headers: authImageHeader()
    })
  }
}
const eventService = new EventService()
export default eventService