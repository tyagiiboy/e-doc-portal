import EventCard from "../components/EventCard"
import eventService from "../service/event.service"
import { useState, useEffect } from "react"
import { useSelector } from "react-redux"

const HomePage = (props) => {
  const user = useSelector(state => state.user)
  const [eventArray, setEventArray] = useState([])

  useEffect(()=> {
    eventService.getAllEvents().then((response) => {
      setEventArray(response.data);
    })
  },[])
  
  return (
    <div class="dark:text-white">
      {eventArray.map(
        (event) => (<EventCard key={event.eventId} event={event} user={user} />)
      )}
    </div>
  )
}

export default HomePage