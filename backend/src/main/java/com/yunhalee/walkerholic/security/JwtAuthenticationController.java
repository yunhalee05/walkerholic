package com.yunhalee.walkerholic.security;


import com.yunhalee.walkerholic.dto.UserDTO;
import com.yunhalee.walkerholic.dto.UserRegisterDTO;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.UserRepository;
import com.yunhalee.walkerholic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
//@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody JwtRequest authenticationRequest) throws Exception{

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        User user = userRepository.findByEmail(userDetails.getUsername());
        UserDTO userDTO = new UserDTO(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userDTO);
        map.put("token", token);

        return ResponseEntity.ok(map);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestParam("firstname")String firstname,
                                    @RequestParam("lastname")String lastname,
                                    @RequestParam("email")String email,
                                    @RequestParam("password")String password,
                                    @RequestParam("phoneNumber")String phoneNumber,
                                    @RequestParam("description")String description,
                                    @RequestParam("isSeller")boolean isSeller,
                                    @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(firstname, lastname,email,password,phoneNumber,description,isSeller);
        UserDTO userDTO = userService.saveUser(userRegisterDTO, multipartFile);
        final String token = jwtTokenUtil.generateToken(userDTO.getEmail());

        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userDTO);
        map.put("token", token);

        return ResponseEntity.ok(map);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Param("token")String token) {
        String email = jwtTokenUtil.getUsernameFromToken(token);
        User user = userRepository.findByEmail(email);
        UserDTO userDTO = new UserDTO(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userDTO);
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println(authentication.getAuthorities().toString());

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}