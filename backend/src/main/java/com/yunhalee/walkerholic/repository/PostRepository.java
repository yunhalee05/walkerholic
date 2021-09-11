package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.user u LEFT JOIN FETCH p.postImages i LEFT JOIN FETCH p.likePosts l LEFT JOIN FETCH l.user WHERE p.id=:id")
    Post findByPostId(Integer id);
}
