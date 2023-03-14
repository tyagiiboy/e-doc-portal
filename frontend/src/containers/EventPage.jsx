import { useState, useEffect } from "react"
import EventCard from "../components/EventCard"
import eventService from "../service/event.service"
import { useSelector } from "react-redux"
import { Role } from "../model/Role"

const EventPage = () => {
	const user = useSelector(state => state.user)

	const [eventArray, setEventArray] = useState([])

	useEffect(() => {
		if (user?.role === Role.SCHOOL || user?.role === Role.MANAGEMENT) {
			eventService.getEventsBySchoolId(user.id).then(
				(response) => setEventArray(response.data)
			).catch(err => console.log(err))
		} else {
			eventService.getAllParticipationsOfUser(user.id).then(
				res => setEventArray(res.data)
			).catch(err => console.log(err))
		}
		console.log(eventArray)
	}, [])

	return (
		<div class="dark:text-white">
			{
				(eventArray.length > 0)? eventArray.map(
					(event) => (<EventCard key={event.id} event={event} user={user} />)
				) : <p class='p-3 text-center text-xl'>{(user.role === Role.SCHOOL)? 'No Events created' : 'No enrolled events'}</p>
			}
		</div>
	)
}

export default EventPage