package com.yunhalee.walkerholic.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ActivityCreateDTO {
    private Integer id;

    private String name;

    private Integer score;

    private String description;

    public ActivityCreateDTO(Integer id, String name, Integer score, String description) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.description = description;
    }

    public ActivityCreateDTO(String name, Integer score, String description) {
        this.name = name;
        this.score = score;
        this.description = description;
    }
}
