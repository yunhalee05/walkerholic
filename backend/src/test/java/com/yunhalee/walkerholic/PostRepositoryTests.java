package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

@Rollback(false)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testGetPostById(){
        Integer id = 1;
        Post post = postRepository.findByPostId(id);
        assertThat(post.getContent(), is(equalTo("first post")));

    }
}