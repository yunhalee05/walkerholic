package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testGetPostByUserId(){
        Integer id = 14;
        List<Post> posts = postRepository.findByUserId(id);
        System.out.println(posts);
    }

    @Test
    public void testGetPostByRandom(){
        Integer id = 3;
        Pageable pageable = PageRequest.of(0, 1);
        Page<Post> pagePost  = postRepository.findByRandom(pageable, id);
        List<Post> posts = pagePost.getContent();
        System.out.println(posts);
    }

    @Test
    public void testDeletePostById(){
        Integer id = 3;
        postRepository.deleteById(id);
    }

    @Test
    public void testGetPostByFollowings(){
        List<Integer> followings = new ArrayList<>();
        followings.add(14);
        Pageable pageable = PageRequest.of(0, 9);

        Page<Post> pagePost = postRepository.findByFollowings(pageable, followings);
        List<Post> posts = pagePost.getContent();
        System.out.println(posts);
    }

    @Test
    public void testGetPostByLikePosts(){
        Pageable pageable = PageRequest.of(0, 9);
        Page<Post> postPage = postRepository.findByLikePostSize(pageable);
        List<Post> posts = postPage.getContent();
        posts.forEach(post -> System.out.println(post.getId()));
    }

}
