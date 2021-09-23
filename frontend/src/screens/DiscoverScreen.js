import React, { useState } from 'react'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import PostThumb from '../components/PostThumb'
import { getDiscoverPosts } from '../_actions/PostActions'

function DiscoverScreen() {

    const dispatch = useDispatch()

    const auth = useSelector(state => state.auth)
    const discover = useSelector(state => state.discover)

    const [page, setPage] = useState(2)

    useEffect(() => {
        dispatch(getDiscoverPosts(1, auth.user.id))
    }, [dispatch])

    const handleLoadMore = () =>{
        dispatch(getDiscoverPosts(page, auth.user.id))
        setPage(page+1)
    }
    
    return (
        <div className="discover">
            <div style={{fontSize:'2.4rem', fontWeight:'800',textAlign:'center' , marginTop:'2rem', marginBottom:"2rem"}}>
                Find Co-Earthsavers!
            </div>
            <PostThumb posts ={discover.posts}/>

            {
                discover.totalPage > page &&
                <button onClick={handleLoadMore}>Load More</button>
            }
        </div>
    )
}

export default DiscoverScreen