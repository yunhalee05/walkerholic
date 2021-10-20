import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import PostCard from '../components/posts/PostCard'
import PostThumb from '../components/PostThumb'
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
            (home.loading===false && home.posts) &&
            <div className="post_screen">
                {/* {
                    home.posts.map((post,index)=>(
                        <PostCard post={post} key={index}/>
                    ))
                } */}
            {
                home.posts &&
                <PostThumb posts ={home.posts}/>
            }
            </div>

        }
        </>
    )
}

export default PostsScreen
