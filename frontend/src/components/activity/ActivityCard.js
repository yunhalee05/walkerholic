import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { Link } from 'react-router-dom'
import earth from '../../images/earth.svg'
import { deleteActivity } from '../../_actions/ActivityActions'
import EditActivity from './EditActivity'

function ActivityCard({activity}) {

    const [isEdit, setIsEdit] = useState(false)

    const dispatch = useDispatch()

    const handleDelete = () =>{
        if(window.confirm('Are you sure to delete this activity?')){
            dispatch(deleteActivity(activity.id))
        }
    }
    return (
        <>
                <div className="activitycard">
                    <div>
                        <button onClick={()=>setIsEdit(!isEdit)}>Edit</button>
                        <button onClick={handleDelete}>Delete</button>
                    </div>
                    <Link to={`/activity/${activity.id}`} style={{display:"flex"}}>
                        <div className="activity_image">
                            <img src={activity.imageUrl?activity.imageUrl : earth} alt="activityImage"/>
                        </div>
                        <div className="activity_info">
                            <div className="activity_name">{activity.name}</div>
                            <div className="activity_score">{activity.score} points</div>
                            <div className="activity_description">{activity.description}</div>
                        </div>
                    </Link>

                </div>
            {
                isEdit &&
                <EditActivity activity={activity} setIsEdit={setIsEdit}/>
            }
        </>
    )
}

export default ActivityCard
