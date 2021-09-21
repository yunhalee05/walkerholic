import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import PostCard from '../components/posts/PostCard'
import { getHomePost } from '../_actions/PostActions'

function PostsScreen() {

    const home = useSelector(state => state.home)

    const [page, setPage] = useState(1)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getHomePost(1))
    }, [dispatch])
    
    return (
        <>
        {
            home.loading===false &&
            <div className="post_screen">
                {
                    home.posts.map((post,index)=>(
                        <PostCard post={post} key={index}/>
                    ))
                }
                
            </div>

        }
        </>
    )
}

export default PostsScreen
