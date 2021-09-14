import React, { useState } from 'react'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import EditPost from '../components/posts/EditPost'
import { getFollowingsPosts } from '../_actions/PostActions'

function PostScreen() {

    const auth = useSelector(state => state.auth)

    const dispatch = useDispatch()

    const [isCreate, setIsCreate] = useState(false)
    const [isEdit, setIsEdit] = useState(false)

    useEffect(() => {
        dispatch(getFollowingsPosts(1, auth.user.id))
    }, [dispatch])
    
    return (
        <div>
            PostCreate
            <button onClick={()=>setIsCreate(true)}>PostCreate</button>

            {
                isCreate &&
                <EditPost/>
            }

            {
                isEdit &&
                <EditPost/>
            }
        </div>
    )
}

export default PostScreen
