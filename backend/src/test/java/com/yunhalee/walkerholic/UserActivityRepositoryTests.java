package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.repository.UserActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Rollback(false)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class UserActivityRepositoryTests {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Test
    public void deleteById(){
        Integer id = 1;
        userActivityRepository.deleteById(1);
    }
}
