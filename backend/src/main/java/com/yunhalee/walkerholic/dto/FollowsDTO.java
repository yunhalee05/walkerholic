package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Follow;
import com.yunhalee.walkerholic.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowsDTO {

    private Integer id;

    private followUser fromUser;

    private followUser toUser;

    public FollowsDTO(Follow follow) {
        this.id = follow.getId();
        this.fromUser = new followUser(follow.getFromUser());
        this.toUser = new followUser(follow.getToUser());
    }

    @Getter
    static class followUser{
        private Integer id;
        private String fullname;
        private String imageUrl;

        public followUser(User user) {
            this.id = user.getId();
            this.fullname = user.getFullname();
            this.imageUrl = user.getImageUrl();
        }
    }
}
