package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.LevelDTO;
import com.yunhalee.walkerholic.entity.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @GetMapping("/")
    public String viewHomePage(){
        return "Hello World";
    }
//    @GetMapping("/api/hello")
//    public HashMap hello(){
//        HashMap result = new HashMap();
//        result.put("message","안녕하세요");
//        return result;
//    }

    @GetMapping("/levels")
    public List<LevelDTO> getLevels(){
        List<LevelDTO> levels = Arrays.stream(Level.values()).map(level -> new LevelDTO(level)).collect(Collectors.toList());
        return levels;
    }
}
