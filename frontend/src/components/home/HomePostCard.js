import React from 'react'
import moment from 'moment'
import {Link} from 'react-router-dom'
import basicProfile from '../../images/basicProfile.svg'
import LikeButton from '../posts/LikeButton'

function HomePostCard({post}) {
    return (
        <div className="home_post">
             <div className="home_postcard_header">
                <div className="home_postcard_user_info">
                    <div>
                        <img src={post.user.imageUrl? post.user.imageUrl : basicProfile} alt="userImage" />
                    </div>
                    <div className="home_postcard_user_name">
                        <div style={{fontSize:"13px", fontWeight:"800"}}>
                            {post.user.fullname}
                        </div>
                        <small>
                            {moment(post.createdAt).fromNow()}
                        </small>
                    </div>
                </div>
            </div>

            <div className="home_cardbody">
                <div className="home_cardbody_image">
                    <img src={post.postImages[0].imageUrl} alt="" />
                </div>
                <div className="home_cardbody_content">
                    {
                        post.content.length>0 && <strong>{post.user.fullname}  </strong>
                    }
                    <span>
                    {
                        post.content.length<20
                        ? post.content
                        :post.content.slice(0,20)+ "..."
                    }
                    </span>
                </div>
        </div>

        <div className="home_cardfooter">
            <div className="home_cardfooter_icon">
                <div>
                    <LikeButton isLike={true} ></LikeButton>
                </div>
                <div>
                    <i class="far fa-paper-plane" ></i>
                </div>
                <div>
                    <i class="far fa-clipboard" ></i>
                </div>
            </div>

            <div className="d-flex justify-content-between" style={{fontSize:"13px"}}>
                <div><strong>{post.postLikes.length}</strong> likes</div>
            </div>

        </div>
        </div>
    )
}

export default HomePostCard
