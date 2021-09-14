package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.FileUploadUtils;
import com.yunhalee.walkerholic.dto.PostCreateDTO;
import com.yunhalee.walkerholic.dto.PostDTO;
import com.yunhalee.walkerholic.dto.UserPostDTO;
import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.entity.PostImage;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.PostImageRepository;
import com.yunhalee.walkerholic.repository.PostRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PostImageRepository postImageRepository;

    public static final int POST_PER_PAGE = 9;


    private void savePostImage(Post post, List<MultipartFile> multipartFiles){
        multipartFiles.forEach(multipartFile -> {
            PostImage postImage = new PostImage();
            try{
                String fileName = System.currentTimeMillis() + StringUtils.cleanPath(multipartFile.getOriginalFilename());
                String uploadDir = "postUploads/" + post.getId();

                postImage.setName(fileName);
                FileUploadUtils.saveFile(uploadDir,fileName,multipartFile);
                postImage.setFilePath("/postUploads/" + post.getId() + "/" + fileName);
                postImage.setPost(post);
                postImageRepository.save(postImage);

            }catch (IOException ex){
                new IOException("Could not save file : " + multipartFile.getOriginalFilename());
            }
        });
    }

    public String savePost(PostCreateDTO postCreateDTO, List<MultipartFile> multipartFiles){
        if(postCreateDTO.getId()!=null){
            Post existingPost = postRepository.findById(postCreateDTO.getId()).get();
            existingPost.setContent(postCreateDTO.getContent());
            if(multipartFiles!=null){
                savePostImage(existingPost, multipartFiles);
            }
            postRepository.save(existingPost);
        }else{
            Post post = new Post();
            User user = userRepository.findById(postCreateDTO.getUserId()).get();
            post.setContent(postCreateDTO.getContent());
            post.setUser(user);
            postRepository.save(post);
            if(multipartFiles!=null){
                savePostImage(post,multipartFiles);
            }
            postRepository.save(post);
        }

        return "Post Saved Successfully.";
    }

    public PostDTO getPost(Integer id){
        Post post = postRepository.findByPostId(id);
        return new PostDTO(post);
    }

    public HashMap<String, Object> getUserPosts(Integer id){
        List<Post> posts = postRepository.findByUserId(id);
        List<UserPostDTO> userPostDTOS = new ArrayList<>();
        posts.forEach(post -> userPostDTOS.add(new UserPostDTO(post)));

        List<Post> likePosts = postRepository.findByLikePostUserId(id);
        List<UserPostDTO> userLikePostDTOS = new ArrayList<>();
        likePosts.forEach(likePost-> userLikePostDTOS.add(new UserPostDTO(likePost)));

        HashMap<String, Object> userPosts = new HashMap<>();
        userPosts.put("posts", userPostDTOS);
        userPosts.put("likePosts",userLikePostDTOS);
        return userPosts;
    }

    public HashMap<String, Object> getPostsByRandom(Integer page, Integer userId){
        Pageable pageable = PageRequest.of(page-1, POST_PER_PAGE);
        Page<Post> pagePost = postRepository.findByRandom(pageable, userId);
        List<Post> posts = pagePost.getContent();
        System.out.println(posts.size());
        List<UserPostDTO> userPostDTOList = new ArrayList<>();
        posts.forEach(post -> userPostDTOList.add(new UserPostDTO(post)));
        HashMap<String, Object> randomPosts = new HashMap<>();
        randomPosts.put("posts",userPostDTOList );
        randomPosts.put("totalElement", pagePost.getTotalElements());
        randomPosts.put("totalPage", pagePost.getTotalPages());
        return randomPosts;
    }

    public String deletePost(Integer id){
        String dir = "/productUploads/"+ id;
        FileUploadUtils.deleteDir(dir);

        postRepository.deleteById(id);
        return "Post Deleted Successfully.";
    }
}
