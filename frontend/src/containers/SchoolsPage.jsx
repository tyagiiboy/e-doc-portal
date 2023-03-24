import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import schoolService from '../service/school.service'
import SchoolCard from '../components/SchoolCard'

const SchoolsPage = () => {
  const user = useSelector(state => state.user)
  const [schools, setSchools] = useState([])


  useEffect(
    () => {

      schoolService.getschools().then(
        res => setSchools(res.data)
      ).catch(
        err => console.log(err)
      )

      console.log(schools)

    }
    , [])

  return (
    <div>
      <div class='overflow-auto'>
        {
          schools.map(
            (school) => (<SchoolCard key={school.diseCode} school={school} />)
          )
        }
      </div>
    </div>
  )
}

export default SchoolsPage