package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.LikePost;
import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.entity.Review;
import com.yunhalee.walkerholic.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PostDTO {

    private Integer id;

    private String content;

    private PostUser user;

    private List<PostLike> postLikes;


    public PostDTO(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.user = new PostUser(post.getUser());
        this.postLikes = PostLike.likeList(post.getLikePosts());
    }

    @Getter
    static class PostUser{
        private Integer id;
        private String fullname;
        private String email;
        private String imageUrl;
        private String description;

        public PostUser(User user) {
            this.id = user.getId();
            this.fullname = user.getFullname();
            this.email = user.getEmail();
            this.imageUrl = user.getImageUrl();
            this.description = user.getDescription();
        }
    }

    @Getter
    static class PostLike{
        private Integer id;
        private String fullname;
        private String imageUrl;

        static List<PostLike> likeList(Set<LikePost> likePosts){
            List<PostLike> postLikeList = new ArrayList<>();
            likePosts.forEach(likePost -> postLikeList.add(new PostLike(likePost)));
            return postLikeList;
        }

        public PostLike(LikePost likePost){
            this.id = likePost.getUser().getId();
            this.fullname = likePost.getUser().getFullname();
            this.imageUrl = likePost.getUser().getImageUrl();
        }

    }
}
