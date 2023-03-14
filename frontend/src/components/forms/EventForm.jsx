import { useState } from 'react'

import { useNavigate } from 'react-router-dom'
import Event from '../../model/Event'
import eventService from '../../service/event.service'
import store from '../../store'


const EventForm = () => {

  const history = useNavigate();
  const [event, setEvent] = useState(new Event())
  const [image, setImage] = useState(null)

  const onChangeHandler = (e) => {
    const { name, value } = e.target;
    setEvent((event) => {
      return {
        ...event,
        [name]: value
      }
    });
  }

  const handleFileChange = (event) => {
    console.log(event.target.files[0])
    setImage(event.target.files[0])
  }

  const submitHandler = (e) => {
    e.preventDefault();
    eventService.createEvent(
      event, store.getState().user.id
    ).then(res => {
      console.log(res.data)
      eventService
        .uploadEventImage(res.data.eventId, image)
        .catch(err => console.log(err))
    })
    history('/');
  }

  return (
    <div class='p-20'>
      <p class='dark:text-white text-3xl mb-11'>Event Details</p>
      <form onSubmit={submitHandler}>
        <div class="grid md:grid-cols-2 md:gap-6">
          <div class="relative z-0 w-full mb-6 group">
            <input onChange={onChangeHandler} type="tel" name="eventName" id="floating_eventName" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="floating_eventName" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Event Name</label>
          </div>
          <div class="relative z-0 w-full mb-6 group">
            <textarea onChange={onChangeHandler} type="tel" name="description" id="floating_description" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="floating_description" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Description</label>
          </div>
          <div class="relative z-0 w-full mb-6 group">
            <input onChange={onChangeHandler} type="date" name="startDate" id="floating_startDate" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="floating_startDate" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Start Date</label>
          </div>
          <div class="relative z-0 w-full mb-6 group">
            <input onChange={onChangeHandler} type="date" name="endDate" id="floating_endDate" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="floating_endDate" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">End Date</label>
          </div>
          <div class="relative z-0 w-full mb-6 group">
            <input onChange={onChangeHandler} type="date" name="lastDateOfEnrollment" id="floating_lastDateOfEnrollment" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
            <label for="floating_lastDateOfEnrollment" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Last Date of Enrollment</label>
          </div>
          <div class="relative z-0 w-full mb-6 group">
            <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-white" for="event_image">Event Image</label>
            <input onChange={handleFileChange} class="block w-full mb-5 text-xs text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400" id="event_image" type="file" />
          </div>
        </div>

        <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button>
      </form>
    </div>
  );
}

export default EventForm;