package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Level;
import com.yunhalee.walkerholic.entity.Role;
import com.yunhalee.walkerholic.entity.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String role;

    private String imageUrl;

    private String phoneNumber;

    private String level;

    private String description;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.role = user.getRole().name();
        this.imageUrl = user.getImageUrl();
        this.phoneNumber = user.getPhoneNumber();
        this.level = user.getLevel().name();
        this.description = user.getDescription();
    }

}
