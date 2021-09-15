import React from 'react'

function PostThumb({posts}) {
    return (
        <div className="post_thumb_container">
            {
                posts.map((post, index)=>(
                    <div className="post_thumb" key={index}>
                        <img src={post.imageUrl} alt="postImage" />
                    </div>
                ))
            }
        </div>
    )
}

export default PostThumb
