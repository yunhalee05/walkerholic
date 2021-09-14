import React from 'react'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getFollowingsPosts } from '../_actions/PostActions'

function PostScreen() {

    const auth = useSelector(state => state.auth)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getFollowingsPosts(1, auth.user.id))
    }, [dispatch])
    
    return (
        <div>
            
        </div>
    )
}

export default PostScreen
