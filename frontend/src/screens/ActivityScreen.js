import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import ActivityCard from '../components/activity/ActivityCard'
import EditActivity from '../components/activity/EditActivity'
import Level from '../components/Level'
import { getActivities } from '../_actions/ActivityActions'

function ActivityScreen() {

    const activity = useSelector(state => state.activity)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getActivities())
    }, [dispatch])

    const [isCreate, setIsCreate] = useState(false)
    
    return (
        <div className="activity">
            <Level/>
            <div>
                <button onClick={()=>setIsCreate(!isCreate)}>Create Activity</button>
            </div>
            {
                activity.loading===false &&
                <div>
                    {
                        activity.activities?.map((activity, index)=>(
                            <ActivityCard activity={activity} key={index}/>
                        ))
                    }
                </div>
            }

            {
                isCreate &&
                <EditActivity  setIsEdit={setIsCreate}/>
            }


        </div>
    )
}

export default ActivityScreen
