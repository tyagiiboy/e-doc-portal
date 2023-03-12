import React from 'react'
import Login from '../components/forms/Login'
import RegisterSchool from '../components/forms/RegisterSchool'
import RegisterUser from '../components/forms/RegisterUser'
import Profile from '../components/Profile'

import {BrowserRouter, Route, Routes} from 'react-router-dom';

const Container = (props) => {
  return (
    <div className='max-w-4xl border-dashed border-2 border-black dark:border-white mx-auto my-3'>
      
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/register' element={<RegisterUser setMessageToast={props.setMessageToast} />} />
        <Route path='/school' element={<RegisterSchool setMessageToast={props.setMessageToast}/>} />
        <Route path='/profile' element={<Profile />} />
      </Routes>
    </BrowserRouter>
      
    </div>
  )
}

export default Container