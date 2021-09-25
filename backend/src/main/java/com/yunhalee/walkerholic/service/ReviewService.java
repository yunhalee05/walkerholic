package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.dto.ReviewCreateDTO;
import com.yunhalee.walkerholic.dto.ReviewDTO;
import com.yunhalee.walkerholic.entity.Product;
import com.yunhalee.walkerholic.entity.Review;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.ReviewRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewDTO saveReview(ReviewCreateDTO reviewCreateDTO){
        if(reviewCreateDTO.getId()!=null){
            Review existingReview = reviewRepository.findByReviewId(reviewCreateDTO.getId());
            if(existingReview.getRating() != reviewCreateDTO.getRating()){
                Product product = productRepository.findById(existingReview.getProduct().getId()).get();
                product.editReview(existingReview.getRating(),reviewCreateDTO.getRating());
                productRepository.save(product);
                existingReview.setRating(reviewCreateDTO.getRating());
            }
            existingReview.setComment(reviewCreateDTO.getComment());
            reviewRepository.save(existingReview);
            return new ReviewDTO(existingReview);
        }else{
            User user = userRepository.findById(reviewCreateDTO.getUserId()).get();
            Product product = productRepository.findById(reviewCreateDTO.getProductId()).get();
            Review review = Review.createReview(reviewCreateDTO.getRating(), reviewCreateDTO.getComment(), user,product);
            product.addReview(review);
            productRepository.save(product);
            reviewRepository.save(review);
            return new ReviewDTO(review);
        }
    }

    public Integer deleteReview(Integer id){
        Review review = reviewRepository.findById(id).get();
        Product product = productRepository.findById(review.getProduct().getId()).get();
        product.deleteReview(review.getRating());
        reviewRepository.deleteById(id);
        return id;
    }
}
