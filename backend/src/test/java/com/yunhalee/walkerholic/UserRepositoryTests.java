package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.dto.LevelDTO;
import com.yunhalee.walkerholic.entity.Level;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.UserRepository;
import com.yunhalee.walkerholic.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;


//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;
    @Autowired
    private UserService userService;


    @Test
    public void testGetUserById() {
        Integer id = 1;
        User user = repo.findByUserId(1);
        assertThat(user.getFirstname(),is(equalTo("lee")));
    }

    @Test
    public void testGetLevel(){
//        Integer id=4;
//        User user = repo.findByUserId(id);
//        System.out.println(user.getLevel().getName());

        List<LevelDTO> levels = Arrays.stream(Level.values()).map(level -> new LevelDTO(level)).collect(Collectors.toList());

        System.out.println(levels);

    }

}
