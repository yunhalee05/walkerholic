package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.dto.LikePostDTO;
import com.yunhalee.walkerholic.entity.LikePost;
import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.LikePostRepository;
import com.yunhalee.walkerholic.repository.PostRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikePostService {

    private final LikePostRepository likePostRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public LikePostDTO likePost(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();

        LikePost likePost = LikePost.likePost(post, user);
        likePostRepository.save(likePost);
        return new LikePostDTO(likePost);
    }

    public Integer unlikePost(Integer id) {
        likePostRepository.deleteById(id);
        return id;
    }
}
