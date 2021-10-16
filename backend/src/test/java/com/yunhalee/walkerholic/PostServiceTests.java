package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.dto.PostCreateDTO;
import com.yunhalee.walkerholic.dto.PostDTO;
import com.yunhalee.walkerholic.dto.UserPostDTO;
import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.repository.FollowRepository;
import com.yunhalee.walkerholic.repository.PostRepository;
import com.yunhalee.walkerholic.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTests {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    FollowRepository followRepository;

    @Test
    public void createPost(){
        //given
        String content = "testContent";
        Integer userId = 1;
        PostCreateDTO postCreateDTO = new PostCreateDTO(content, userId);
        MultipartFile multipartFile = new MockMultipartFile("uploaded-file",
                "sampleFile.txt",
                "text/plain",
                "This is the file content".getBytes());
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(multipartFile);

        //when
        PostDTO postDTO = postService.savePost(postCreateDTO, multipartFiles);

        //then
        assertNotNull(postDTO.getId());
        assertEquals(postDTO.getContent(), content);
        assertNotNull(postDTO.getPostImages());
        assertEquals(postRepository.findById(postDTO.getId()).get().getPostImages().get(0).getName(),"sampleFile.txt");
    }

    @Test
    public void updatePost(){
        //given
        Integer postId = 1;
        Post post = postRepository.findById(postId).get();
        String originalContent = post.getContent();
        String newContent = "updateTestPost";
        post.setContent(newContent);
        PostCreateDTO postCreateDTO = new PostCreateDTO(post.getId(), post.getContent(), post.getUser().getId());

        //when
        PostDTO postDTO = postService.savePost(postCreateDTO, null);

        //then
        assertEquals(postDTO.getId(), postId);
        assertNotEquals(postDTO.getContent(), originalContent);
        assertEquals(postDTO.getContent(),newContent);
    }

    @Test
    public void getPostById(){
        //given
        Integer postId = 1;

        //when
        PostDTO postDTO = postService.getPost(postId);

        //then
        assertEquals(postDTO.getId(),postId);
    }

    @Test
    public void getPostsByUserId(){
        //given
        Integer userId = 1;

        //when
        HashMap<String, Object> response = postService.getUserPosts(userId);
        List<UserPostDTO> posts = (List<UserPostDTO>) response.get("posts");
        List<UserPostDTO> likePosts = (List<UserPostDTO>) response.get("likePosts");

        //then
        for (UserPostDTO post : posts) {
            assertEquals(postRepository.findById(post.getId()).get().getUser().getId(), userId);
        }
        for (UserPostDTO likePost : likePosts) {
            List<Integer> likeIds = postRepository.findById(likePost.getId()).get().getLikePosts().stream().map(likePost1 -> likePost1.getUser().getId()).collect(Collectors.toList());
            assertThat(likeIds).contains(userId);
        }
    }

    @Test
    public void getPostsByRandom(){
        //given
        Integer userId = 1;
        Integer page = 1;

        //when
        HashMap<String, Object> response = postService.getPostsByRandom(page, userId);
        List<PostDTO> postDTOS = (List<PostDTO>) response.get("posts");

        //then
        for (PostDTO postDTO : postDTOS) {
            assertNotEquals(postRepository.findById(postDTO.getId()).get().getUser().getId(), userId);
        }
    }

    @Test
    public void getHomePosts(){
        //given
        Integer page = 1;

        //when
        HashMap<String, Object> response = postService.getHomePosts(page);
        List<PostDTO> postDTOS = (List<PostDTO>) response.get("posts");

        //then
        Integer priorLikes = postDTOS.get(0).getPostLikes().size();
        for(int i = 1; i<postDTOS.size(); i++){
            assertThat(postDTOS.get(i).getPostLikes().size()).isLessThan(priorLikes);
            priorLikes = postDTOS.get(i).getPostLikes().size();
        }
    }

    @Test
    public void getPostsByFollowings(){
        //given
        Integer userId = 1;
        Integer page = 1;

        //when
        HashMap<String, Object> response = postService.getPostsByFollowings(page, userId);
        List<PostDTO> postDTOS = (List<PostDTO>) response.get("posts");
        List<Integer> followings = followRepository.findAllByFromUserId(userId).stream().map(follow -> follow.getToUser().getId()).collect(Collectors.toList());

        //then
        for (PostDTO postDTO : postDTOS) {
            assertThat(followings).contains(postRepository.findById(postDTO.getId()).get().getUser().getId());
        }
    }

    @Test
    public void deletePost(){
        //given
        Integer id = 1;

        //when
        postRepository.deleteById(id);

        //then
        assertNull(postRepository.findById(id));
    }
}
