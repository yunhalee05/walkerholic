package com.yunhalee.walkerholic;


import com.yunhalee.walkerholic.entity.Follow;
import com.yunhalee.walkerholic.repository.FollowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Rollback(false)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class FollowRepostioryTests {

    @Autowired
    FollowRepository followRepository;

    @Test
    public void testGetFollowByUserId(){
        Integer id = 1;
        List<Follow> follows = followRepository.findAllByUserId(id);
        System.out.println(follows);
    }

}
