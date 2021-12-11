package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.LevelDTO;
import com.yunhalee.walkerholic.entity.Category;
import com.yunhalee.walkerholic.entity.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Value("${paypal_client_id}")
    private String PAYPAL_CLIENT_ID;

    @GetMapping("/levels")
    public List<LevelDTO> getLevels(){
        List<LevelDTO> levels = Arrays.stream(Level.values()).map(level -> new LevelDTO(level)).collect(Collectors.toList());
        return levels;
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return Arrays.stream(Category.values()).map(category -> category.name()).collect(Collectors.toList());
    }

    @GetMapping("/paypal")
    public String getPAYPAL_CLIENT_ID(){
        return PAYPAL_CLIENT_ID;
    }
}
