package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.user u LEFT JOIN FETCH p.postImages i LEFT JOIN FETCH p.likePosts l LEFT JOIN FETCH l.user WHERE p.id=:id")
    Post findByPostId(Integer id);

    @Query(value = "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.postImages i LEFT JOIN FETCH p.likePosts l LEFT JOIN FETCH p.user u WHERE u.id=:id")
    List<Post> findByUserId(Integer id);

    @Query(value = "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.postImages i LEFT JOIN FETCH p.likePosts l LEFT JOIN FETCH l.user u WHERE u.id=id")
    List<Post> findByLikePostUserId(Integer id);

    @Query(value = "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.postImages i LEFT JOIN FETCH p.likePosts LEFT JOIN FETCH p.user u WHERE NOT u.id=:id ORDER BY RAND()",
            countQuery = "SELECT count(DISTINCT p) FROM Post p")
    Page<Post> findByRandom(Pageable pageable, Integer id);
}
