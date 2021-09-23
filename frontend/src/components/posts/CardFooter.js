import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import LikeButton from './LikeButton'
import {Link} from 'react-router-dom'
import ShareModal from '../ShareModal'
import { BASE_URL } from '../../utils/Url'
import { likePost, unlikePost } from '../../_actions/PostActions'

function CardFooter({post}) {

    const auth = useSelector(state => state.auth)

    const [isLike, setIsLike] = useState(post.postLikes.filter(like=>like.userId===auth.user.id).length===1 ? true:false)
    const [isShare, setIsShare] = useState(false)

    const dispatch = useDispatch()

    const handleCopyLink= () =>{
        navigator.clipboard.writeText(`${BASE_URL}/post/${post.id}`)
    }

    const handleLike = () =>{
        const likepost = post.postLikes.filter(like=>like.userId===auth.user.id)

        if(!isLike){
            dispatch(likePost(post.id)).then(res=>{
                setIsLike(!isLike)
            })
        }else{
            dispatch(unlikePost(post.id, likepost[0].id)).then(res=>{
                setIsLike(!isLike)
            })
        }

    }

    return (
        <div className="cardfooter">
            <div className="cardfooter_icon">
                <div>
                    <LikeButton isLike={isLike} handleLike={handleLike} ></LikeButton>
                </div>
                {/* <Link to={`/post/${post.id}`} className="text-dark">
                        <i className="far fa-comment"></i>
                </Link> */}
                <div>
                    <i class="far fa-paper-plane" onClick={()=>setIsShare(!isShare)}></i>
                </div>
                <div>
                    <i class="far fa-clipboard" onClick={()=>handleCopyLink}></i>
                </div>
            </div>

            <div className="d-flex justify-content-between">
                <h6>{post.postLikes.length} likes</h6>
            </div>

            {
                isShare &&
                <ShareModal url={`/${BASE_URL}/post/${post.id}`} setIsShare={setIsShare}></ShareModal>
            }
        </div>
    )
}

export default CardFooter