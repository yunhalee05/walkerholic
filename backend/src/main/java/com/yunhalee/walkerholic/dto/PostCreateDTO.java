package com.yunhalee.walkerholic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDTO {

    private Integer id;

    private String content;

    private Integer userId;

    public PostCreateDTO(Integer id, String content, Integer userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    public PostCreateDTO(String content, Integer userId) {
        this.content = content;
        this.userId = userId;
    }
}
