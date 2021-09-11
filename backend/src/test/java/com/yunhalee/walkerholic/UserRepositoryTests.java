package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.dto.UserRegisterDTO;
import com.yunhalee.walkerholic.entity.Role;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.UserRepository;
import com.yunhalee.walkerholic.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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
    public void testEnumType() throws IOException {
//        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("yunha","lee", "member@example.com","123456");
//
//        userService.saveUser(userRegisterDTO,null);

    }
}
