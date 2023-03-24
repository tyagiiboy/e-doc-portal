import React from 'react'

const SchoolCard = ({school}) => {
  return (
    <section class="p-3">
      
      <figure class="md:flex bg-slate-100 rounded-xl p-8 md:p-0 dark:bg-slate-800">
        <img class="w-24 h-24 md:w-48 md:h-auto md:rounded-xl md:rounded-r-none rounded-xl mx-auto " src={process.env.PUBLIC_URL + '/images/school.jpg'} alt="" width="384" height="512" />
        <div class="pt-6 md:p-8 text-center md:text-left space-y-3 grow">
          <figcaption class="font-medium grid md:grid-cols-3 md:gap-6">
            <div>
              Name: {school.name}
            </div>
            <div>
              Disecode: {school.diseCode}
            </div>
            <div>
              Email: {school.email}
            </div>
            <div>
              Co-Ed status: {school.coEd}
            </div>
            <div>
              Level: {school.level}
            </div>
            <div>
              Address: {school.address}
            </div>
          </figcaption>
        </div>
      </figure>
    </section>
  )
}

export default SchoolCard