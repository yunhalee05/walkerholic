import React from 'react'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getDiscoverPosts } from '../_actions/PostActions'

function DiscoverScreen() {

    const dispatch = useDispatch()

    const auth = useSelector(state => state.auth)

    useEffect(() => {
        dispatch(getDiscoverPosts(1, auth.user.id))
    }, [dispatch])
    
    return (
        <div>
            
        </div>
    )
}

export default DiscoverScreen
