import {useEffect, useState} from 'react'
import ProfileCard from '../components/ProfileCard'
import { useSelector } from 'react-redux'
import userService from '../service/user.service'
import schoolService from '../service/school.service'
import SchoolCard from '../components/SchoolCard'

const UserProfile = props => {
    const user = useSelector(store => store.user)
    const [userDto, setUserDto] = useState({})
    const [school, setSchool] = useState({})

    useEffect(function () {
        userService.getprofile(user.id).then(
            res => setUserDto(res.data)
        ).catch(
            err => console.log(err)
        )
        schoolService.getSchool(userDto?.diseCode).then(
            res => setSchool(res.data)
        ).catch(
            err => console.log(err)
        )
    }, [])
    return (
        <div>
            <ProfileCard userDto={userDto}/>
            <SchoolCard school={school} />
        </div>
    )
}

UserProfile.propTypes = {}

export default UserProfile