package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Level;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LevelDTO {

    private String name;

    private Integer min;

    private Integer max;

    public LevelDTO(Level level) {
        this.name = level.getName();
        this.min = level.getMin();
        this.max = level.getMax();
    }
}
