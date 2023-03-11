import React from 'react'
import Login from '../components/forms/Login'
import RegisterSchool from '../components/forms/RegisterSchool'
import RegisterUser from '../components/forms/RegisterUser'
import Modal from '../components/Modal'

import {BrowserRouter, Route, Routes} from 'react-router-dom';

const Container = (props) => {
  return (
    <div className='container max-w-4xl border-dashed border-2 border-black dark:border-white mx-auto max-h-full'>
      
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/register' element={<RegisterUser setMessageToast={props.setMessageToast} />} />
      </Routes>
    </BrowserRouter>
      {/* <Login />
      <RegisterUser />
      <RegisterSchool />
      <Modal /> */}
    </div>
  )
}

export default Container