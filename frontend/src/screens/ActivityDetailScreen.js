import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getActivity } from '../_actions/ActivityActions'
import earth from '../images/earth.svg'
import ActivityUserCard from '../components/activity/ActivityUserCard'


function ActivityDetailScreen(props) {

    const id = props.match.params.id

    const activity = useSelector(state => state.activity)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getActivity(id))
    }, [dispatch])

    return (
        <div className="activity_detail">
            {
                activity.activity.id &&
                <>
                    <div className="activity_detail_info">
                        <div style={{textAlign:"center",fontWeight:"800",fontSize:"2rem"}}>
                            {activity.activity.name}
                        </div>
                        <div className="activity_detail_image">
                            <img src={activity.activity.imageUrl?activity.activity.imageUrl : earth} alt="activityImage"/>
                        </div>
                        <div style={{textAlign:"right",fontWeight:"600", fontSize:"1.2rem"}}>
                            {activity.activity.score} points activity
                        </div>
                        <div style={{}}>
                            {activity.activity.description}
                        </div>
                    </div>

                    <hr/>
                    <div>
                        {
                            activity.activity.activityUsers.map((user,index)=>(
                                <ActivityUserCard user={user} key={index}/>
                            ))
                        }
                    </div>
                </>
            }
        </div>
    )
}

export default ActivityDetailScreen