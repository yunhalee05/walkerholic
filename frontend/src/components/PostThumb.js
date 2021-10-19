import React from 'react'
import earth from '../images/earth.svg'

function PostThumb({posts}) {
    return (
        <div className="post_thumb_container">
            {
                posts.map((post, index)=>(
                    <div className="post_thumb" key={index}>
                        <div className="post_thumb_image">
                            <img src={post.imageUrl} alt="postImage" />
                        </div>
                        <div className="post_thumb_title">{post.title}</div>
                        <div className="post_thumb_user">
                            <div className="post_thumb_user_image">
                                <img src={post.userImageUrl ? post.userImageUrl : earth} alt="" />
                            </div>
                            <div style={{marginLeft:"7px"}}>{post.userName}</div>
                        </div>
                    </div>
                ))
            }
        </div>
    )
}

export default PostThumb
