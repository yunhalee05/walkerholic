import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import PostThumb from '../components/PostThumb'
import { getHomePost, getSearchPosts } from '../_actions/PostActions'

function PostsScreen(props) {

    const keyword = props.match.params.keyword

    const home = useSelector(state => state.home)

    const [page, setPage] = useState(1)
    const [sort, setSort] = useState('popular')

    const dispatch = useDispatch()

    useEffect(() => {
        if(keyword){
            dispatch(getSearchPosts(1, sort, keyword))
        }else{
            dispatch(getHomePost(1, sort))
        }
    }, [dispatch, keyword, sort])
    
    return (
        <>
        <div className="product_sort">
            <span>SORT BY : </span>
            <select value={sort} onChange={e=>setSort(e.target.value)}>
                <option value="newest">Newest</option>
                <option value="popular">Most Popular</option>
            </select>
        </div>
        {
            (home.loading===false && home.posts) &&
            <div className="post_screen">
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
