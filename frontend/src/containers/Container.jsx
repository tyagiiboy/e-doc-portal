import React from 'react'
import Login from '../components/forms/Login'
import RegisterSchool from '../components/forms/RegisterSchool'
import RegisterUser from '../components/forms/RegisterUser'
import UserProfile from '../containers/UserProfile'
import EventPage from './EventPage'
import HomePage from './HomePage'
import EventForm from '../components/forms/EventForm'
import { AuthGuard } from '../guard/auth.guard'
import { Role } from '../model/Role'
import TransferTable from '../components/TransferTable'
import SchoolsPage from './SchoolsPage'

import { BrowserRouter, Route, Routes } from 'react-router-dom';

const Container = (props) => {
  return (
    <div className='max-w-4xl border-dashed border-2 border-black dark:border-white mx-auto my-3'>

      <BrowserRouter>
        <Routes>

          <Route path='/login' element={<Login />} />
          <Route path='/registerUser' element={<RegisterUser setMessageToast={props.setMessageToast} />} />
          <Route path='/registerSchool' element={<RegisterSchool setMessageToast={props.setMessageToast} />} />

          <Route path="/" element={
            <AuthGuard>
              <HomePage />
            </AuthGuard>
          }/>

          <Route path="/profile" element={
            <AuthGuard>
              <UserProfile />
            </AuthGuard>
          }/>
          
          <Route path="/event" element={
            <AuthGuard>
              <EventPage />
            </AuthGuard>
          }/>

          <Route path="/newEvent" element={
            <AuthGuard roles={[Role.SCHOOL]}>
              <EventForm />
            </AuthGuard>
          }/>

          <Route path="/requests" element={
            <AuthGuard roles={[Role.SCHOOL]}>
              <TransferTable />
            </AuthGuard>
          }/>

          <Route path="/schools" element={
            <AuthGuard roles={[Role.STUDENT, Role.ADMIN]}>
              <SchoolsPage />
            </AuthGuard>
          }/>


        </Routes>
      </BrowserRouter>

    </div>
  )
}

export default Container