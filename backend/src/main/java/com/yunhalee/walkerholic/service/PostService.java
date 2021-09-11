package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.FileUploadUtils;
import com.yunhalee.walkerholic.dto.PostCreateDTO;
import com.yunhalee.walkerholic.dto.PostDTO;
import com.yunhalee.walkerholic.entity.Post;
import com.yunhalee.walkerholic.entity.PostImage;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.PostImageRepository;
import com.yunhalee.walkerholic.repository.PostRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PostImageRepository postImageRepository;

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

    public String deletePost(Integer id){
        String dir = "/productUploads/"+ id;
        FileUploadUtils.deleteDir(dir);

        postRepository.deleteById(id);
        return "Post Deleted Successfully.";
    }
}
