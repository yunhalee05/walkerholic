package com.yunhalee.walkerholic.controller;


import com.yunhalee.walkerholic.dto.ReviewCreateDTO;
import com.yunhalee.walkerholic.dto.ReviewDTO;
import com.yunhalee.walkerholic.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review/save")
    public ResponseEntity<?> saveReview(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        return new ResponseEntity<ReviewDTO>(reviewService.saveReview(reviewCreateDTO),
            HttpStatus.OK);
    }

    @DeleteMapping("/review/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable("id") String id) {
        Integer reviewId = Integer.parseInt(id);
        return new ResponseEntity<Integer>(reviewService.deleteReview(reviewId), HttpStatus.OK);
    }
}
