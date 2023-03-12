import React from 'react'

const Profile = () => {
  return (
    <div class="p-3">
      <figure class="md:flex bg-slate-100 rounded-xl p-8 md:p-0 dark:bg-slate-800">
        <img class="w-24 h-24 md:w-48 md:h-auto md:rounded-xl md:rounded-r-none rounded-xl mx-auto " src={process.env.PUBLIC_URL + '/images/sarah-dayan.jpg'} alt="" width="384" height="512" />
        <div class="pt-6 md:p-8 text-center md:text-left space-y-4 grow">
          <figcaption class="font-medium grid md:grid-cols-3 md:gap-6">
            <div class="text-sky-500 dark:text-sky-400">
              Sarah Dayan
            </div>
            <div class="text-slate-700 dark:text-slate-500">
              Staff Engineer, Algolia
            </div>
          </figcaption>
        </div>
      </figure>
    </div>
  )
}

export default Profile