import React from 'react'
import { useHistory } from 'react-router-dom'
import basicProfile from '../../images/basicProfile.svg'

function UserCard({user,setSearchUser}) {

    const history = useHistory()

    const handleOnClick = () =>{
        setSearchUser([])
        history.push(`/user/${user.id}`)
    }
    return (
        <div className="header_search_user" onClick={handleOnClick}>
            <div className="header_search_user_image">
                <img src={user.imageUrl ? user.imageUrl : basicProfile} alt="" />
            </div>
            <div className="header_search_user_name">
                {user.firstname}{user.lastname}
            </div>
        </div>
    )
}

export default UserCard
