import React from 'react'
import Login from '../components/forms/Login'
import RegisterSchool from '../components/forms/RegisterSchool'
import RegisterUser from '../components/forms/RegisterUser'
import Profile from '../components/Profile'
import EventPage from './EventPage'
import HomePage from './HomePage'
import EventForm from '../components/forms/EventForm'

import {BrowserRouter, Route, Routes} from 'react-router-dom';

const Container = (props) => {
  return (
    <div className='max-w-4xl border-dashed border-2 border-black dark:border-white mx-auto my-3'>
      
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<Login navMenuState={props.navMenuState} setNavMenuState={props.setNavMenuState} />}/>
        <Route path='/register' element={<RegisterUser setMessageToast={props.setMessageToast} />} />
        <Route path='/school' element={<RegisterSchool setMessageToast={props.setMessageToast}/>} />
        <Route path='/profile' element={<Profile />} />
        <Route path='/event' element={<EventPage />} />
        <Route path='/' element={<HomePage />} />
        <Route path='/newEvent' element={<EventForm />} />
      </Routes>
    </BrowserRouter>
      
    </div>
  )
}

export default Container