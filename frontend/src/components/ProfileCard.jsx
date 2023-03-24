import React, { useEffect, useState } from 'react'

const ProfileCard = ({userDto}) => {

  return (
    <section class="p-3">
      <figure class="md:flex bg-slate-100 rounded-xl p-8 md:p-0 dark:bg-slate-800">
        <img class="w-24 h-24 md:w-48 md:h-auto md:rounded-xl md:rounded-r-none rounded-xl mx-auto " src={process.env.PUBLIC_URL + '/images/sarah-dayan.jpg'} alt="" width="384" height="512" />
        <div class="pt-6 md:p-8 text-center md:text-left space-y-3 grow">
          <figcaption class="font-medium grid md:grid-cols-3 md:gap-6">
            <div>
              First name: {userDto.firstName}
            </div>
            <div>
              Last name: {userDto.lastName}
            </div>
            <div>
              Email: {userDto.email}
            </div>
            <div>
              username: {userDto.username}
            </div>
            <div>
              Contact: {userDto.contactNo}
            </div>
            <div>
              Address: {userDto.address}
            </div>
            {/* <div class={`${(user?.role === 'ROLE_STUDENT') ? '' : 'hidden'}`}>
              <a href="#" onClick={handleParticipate} class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Enroll</a>
            </div> */}
          </figcaption>
        </div>
      </figure>
    </section>
  )
}

export default ProfileCard