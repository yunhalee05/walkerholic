import React from 'react'
import { Link } from 'react-router-dom'
import basicProfile from '../../images/basicProfile.svg'

function ActivityUserCard({userActivity}) {
    return (
        <div>

            <Link to={`/user/${userActivity.userId}`}>
                <div style={userActivity.status==='ONGOING'? {color:"#e6857e"}:{color:"#58c4a9"}}>
                    {userActivity.status}
                </div>
                <div>
                    <img src={userActivity.userImageUrl? userActivity.userImageUrl : basicProfile} alt="" />
                </div>
                <div>
                    {userActivity.userFullname}
                </div>
            </Link>
        </div>
    )
}

export default ActivityUserCard
