package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.FileUploadUtils;
import com.yunhalee.walkerholic.dto.UserDTO;
import com.yunhalee.walkerholic.dto.UserListDTO;
import com.yunhalee.walkerholic.dto.UserRegisterDTO;
import com.yunhalee.walkerholic.entity.Level;
import com.yunhalee.walkerholic.entity.Role;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.exception.UserEmailAlreadyExistException;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public static final int USER_LIST_PER_PAGE = 10;


    private void saveProfileFile(MultipartFile multipartFile, User user, boolean isNew) throws IOException {
        try{
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "profileUploads/" + user.getId();

            if (!isNew) {
                FileUploadUtils.cleanDir(uploadDir);
            }
            FileUploadUtils.saveFile(uploadDir,fileName,multipartFile);
            user.setImageUrl("/profileUploads/" + user.getId() + "/" + fileName);

        }catch (IOException ex){
            new IOException("Could not save file : " + multipartFile.getOriginalFilename());
        }

    }

    private boolean isEmailUnique(Integer id, String email){
        User existingUser = userRepository.findByEmail(email);

        //존재하지 않는 이메일 선택시 true
        if(existingUser==null){
            return true;
        }
        boolean isCreatingNew = (id==null);

        //이미 존재하는 이메일의 경우 회원의 신입 여부에 따라서 경우를 나눠준다.
        if(isCreatingNew){        //새로운 가입하려는 경우
            if(existingUser != null) return false;
        }else{                    //기존회원의 이메일변경 확인하는 경우
            if(existingUser.getId() != id) return false;
        }

        return true;

    }

    public UserDTO saveUser(UserRegisterDTO userRegisterDTO, MultipartFile multipartFile) throws IOException {

        if(!isEmailUnique(userRegisterDTO.getId(), userRegisterDTO.getEmail())){
            throw new UserEmailAlreadyExistException("Email already exists : " + userRegisterDTO.getEmail());
        }

        if(userRegisterDTO.getId()!=null){  //기존회원의 프로필 수정하는 경우
            User existingUser = userRepository.findById(userRegisterDTO.getId()).get();
            existingUser.setFirstname(userRegisterDTO.getFirstname());
            existingUser.setLastname(userRegisterDTO.getLastname());
            existingUser.setEmail(userRegisterDTO.getEmail());
            if(userRegisterDTO.getPassword()!=null){
                existingUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
            }
            if(userRegisterDTO.isSeller()){
                existingUser.setRole(Role.SELLER);
            }else{
                existingUser.setRole(Role.USER);
            }
            existingUser.setDescription(userRegisterDTO.getDescription());
            existingUser.setPhoneNumber(userRegisterDTO.getPhoneNumber());

            if(multipartFile!=null){
                saveProfileFile(multipartFile, existingUser, false);
            }

            userRepository.save(existingUser);

            return new UserDTO(existingUser);

        }else{  //새로운회원을 등록하는 경우
            User user = new User();
            user.setFirstname(userRegisterDTO.getFirstname());
            user.setLastname(userRegisterDTO.getLastname());
            user.setEmail(userRegisterDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
            user.setDescription(userRegisterDTO.getDescription());
            user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
            if(userRegisterDTO.isSeller()){
                user.setRole(Role.SELLER);
            }else{
                user.setRole(Role.USER);
            }
            user.setLevel(Level.Starter);

            //새로 생성한 유저의 id를 가져오기 위해서 미리 한번 저장해준다.
            userRepository.save(user);

            if(multipartFile!=null){
                saveProfileFile(multipartFile,user, true);
            }

            userRepository.save(user);

            return new UserDTO(user);
        }
    }

    public UserDTO getUser(Integer id){
        User user = userRepository.findByUserId(id);
        UserDTO userDTO = new UserDTO(user);

        return new UserDTO(user);
    }

    public HashMap<String, Object> getUsers(Integer page, String sort){
        Pageable pageable = PageRequest.of(page-1,USER_LIST_PER_PAGE, Sort.by(sort));
        Page<User> userPage = userRepository.findAllUsers(pageable);
        List<User> users = userPage.getContent();
        List<UserListDTO> userListDTOS = new ArrayList<>();
        users.forEach(user -> userListDTOS.add(new UserListDTO(user)));
        HashMap<String, Object> userList = new HashMap<>();
        userList.put("users",userListDTOS);
        userList.put("totalElement", userPage.getTotalElements());
        userList.put("totalPage", userPage.getTotalPages());
        return userList;
    }

    public Integer deleteUser(Integer id){
        userRepository.deleteById(id);
        String dir = "profileUploads/" + id;
        FileUploadUtils.deleteDir(dir);
        return id;
    }


}
