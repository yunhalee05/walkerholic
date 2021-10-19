package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostImageRepository extends JpaRepository<PostImage, Integer> {

    @Transactional
    Integer deleteByFilePath(String filePath);
}
