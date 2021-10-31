import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import PostCard from '../components/posts/PostCard'
import { getPost } from '../_actions/PostActions'

function PostDetailScreen(props) {

    const id = props.match.params.id

    const post = useSelector(state => state.post)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(getPost(id))
    }, [dispatch, id])
    return (
        <div className="post_screen">
            {
                post.loading ===false && 
                    <PostCard post={post.post}/>

            }
        </div>
    )
}

export default PostDetailScreen
