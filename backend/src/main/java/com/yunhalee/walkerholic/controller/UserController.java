package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.UserDTO;
import com.yunhalee.walkerholic.dto.UserRegisterDTO;
import com.yunhalee.walkerholic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/user/save")
    public ResponseEntity<UserDTO> saveUser(@RequestParam("id")Integer id,
                                            @RequestParam("firstname")String firstname,
                                            @RequestParam("lastname")String lastname,
                                            @RequestParam("email")String email,
                                            @RequestParam("password")String password,
                                            @RequestParam("phoneNumber")String phoneNumber,
                                            @RequestParam("description")String description,
                                            @RequestParam("isSeller")boolean isSeller,
                                            @RequestParam("multipartFile")MultipartFile multipartFile
                                                    ) throws IOException {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(id, firstname, lastname,email,password,phoneNumber,description,isSeller);
        return new ResponseEntity<UserDTO>(userService.saveUser(userRegisterDTO,multipartFile), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id")String id){
        Integer userId = Integer.parseInt(id);
        return new ResponseEntity<UserDTO>(userService.getUser(userId), HttpStatus.OK);
    }



}
