package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.LikePost;
import com.yunhalee.walkerholic.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserPostDTO {

    private Integer id;

    private String content;

    private String imageUrl;

    private Integer postLikes;

    public UserPostDTO(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.imageUrl = post.getPostImages().get(0).getFilePath();
        this.postLikes = post.getLikePosts().size();
    }

}
