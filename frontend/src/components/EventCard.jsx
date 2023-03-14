import { useState, useEffect } from 'react'
import eventService from '../service/event.service';

const EventCard = ({ event, user }) => {

  const [imageDataUrl, setImageDataUrl] = useState(null)

  useEffect(() => {
    eventService.downloadEventImage(event.eventId)
      .then((response) => {
        const blob = new Blob([response.data], { type: response.data.type });
        const dataUrl = URL.createObjectURL(blob);
        setImageDataUrl(dataUrl);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const handleParticipate = (e) => {
    e.preventDefault()

    eventService.participateEvent(event.eventId, user.id).then(res => {
      if (res.status === 400) alert('Not yet admitted to anyschool!')
    }).catch(alert('You are not admitted in School'))
    
  }

  function formatDate(dateString) {
    const date = new Date(dateString);
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Intl.DateTimeFormat('en-US', options).format(date);
  }

  return (
    <div class='p-3'>
      <figure class="md:flex bg-slate-100 rounded-xl p-8 md:p-0 dark:bg-slate-800">
        <img class="w-32 h-32 md:w-48 md:h-auto md:rounded-r-none rounded-xl mx-auto" src={imageDataUrl} alt="" width="384" height="512" />
        <div class="pt-6 md:p-8 text-center md:text-left space-y-4 md:grow">
          <figcaption class="font-medium grid md:grid-cols-2 md:gap-6 dark:text-slate-300">
            <div>
              {event.eventName}
            </div>
            <div>
              Description: {event.description}
            </div>
            <div>
              Start at: {formatDate(event.startDate)}
            </div>
            <div>
              End at: {formatDate(event.endDate)}
            </div>
            <div>
              Last date for enrollment: {formatDate(event.lastDateOfEnrollment)}
            </div>
            <div>
              Organizer: {event.organizer.name}
            </div>
            <div class={`${(user?.role === 'ROLE_STUDENT') ? '' : 'hidden'}`}>
              <a href="#" onClick={handleParticipate} class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Enroll</a>
            </div>
          </figcaption>
        </div>
      </figure>
    </div>
  )
}

export default EventCard