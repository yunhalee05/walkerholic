package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.LikePost;
import com.yunhalee.walkerholic.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikePostDTO {

    private Integer id;

    private Integer postId;

    private UserLikePost user;

    public LikePostDTO(LikePost likePost) {
        this.id = likePost.getId();
        this.postId = likePost.getPost().getId();
        this.user = new UserLikePost(likePost.getUser());
    }

    @Getter
    static class UserLikePost {

        private Integer id;
        private String fullname;
        private String imageUrl;

        public UserLikePost(User user) {
            this.id = user.getId();
            this.fullname = user.getFullname();
            this.imageUrl = user.getImageUrl();
        }
    }
}
