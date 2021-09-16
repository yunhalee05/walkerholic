package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Integer> {
}
