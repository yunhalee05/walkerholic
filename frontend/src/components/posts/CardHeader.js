import React, { useState } from 'react'
import { useSelector } from 'react-redux'
import EditPost from './EditPost'
import {Link} from 'react-router-dom'
import moment from 'moment'
import basicProfile from '../../images/basicProfile.svg'


function CardHeader({post}) {

    const auth = useSelector(state => state.auth)

    const [isEdit, setIsEdit] = useState(false)

    const handleEditPost = ()=>{

    }

    const handleDeletePost = () =>{

    }
    return (
        <div className="postcard_header">
            <div className="postcard_user_info">
                <div>
                    <img src={post.user.imageUrl? post.user.imageUrl : basicProfile} alt="userImage" />
                </div>
                <div className="postcard_user_name">
                    <h6>
                        <Link to={`/user/${post.user.id}`}>{post.user.fullname}</Link>
                    </h6>
                    <small>
                        {moment(post.createdAt).fromNow()}
                    </small>
                </div>
            </div>

            {
                auth.user.id === post.user.id &&
                <div className="nav-item dropdown">
                    <span className="material-icons" id="moreLink" data-toggle="dropdown">
                        more_horiz
                    </span>
                    <div className="dropdown-menu">
                                <div className="dropdown-item" onClick={()=>setIsEdit(!isEdit)}>
                                    Edit Post
                                </div>
                                <div className="dropdown-item">
                                    Delete Post
                                </div>
                    </div>
                </div>
            }
            {
                isEdit &&
                <EditPost setIsEdit={setIsEdit} post={post}/>
            }
        </div>
    )
}

export default CardHeader
