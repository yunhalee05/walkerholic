package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.dto.FollowDTO;
import com.yunhalee.walkerholic.entity.Follow;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.FollowRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public String follow(Integer fromId, Integer toId){
        User fromUser = userRepository.findById(fromId).get();
        User toUser = userRepository.findById(toId).get();

        Follow follow = Follow.follow(fromUser, toUser);

        followRepository.save(follow);
        return "Followed User Successfully.";
    }

    public String unfollow(Integer id){
        followRepository.deleteById(id);
        return "Unfollowed User Successfully.";
    }

    public List<FollowDTO> getFollowers(Integer id){
        List<Follow> follows = followRepository.findAllByToUserId(id);
        List<FollowDTO> followDTOS = new ArrayList<>();
        follows.forEach(follow -> followDTOS.add(new FollowDTO(follow.getId(), follow.getFromUser())));
        return followDTOS;
    }

    public List<FollowDTO> getFollowings(Integer id){
        List<Follow> follows = followRepository.findAllByFromUserId(id);
        List<FollowDTO> followDTOS = new ArrayList<>();
        follows.forEach(follow -> followDTOS.add(new FollowDTO(follow.getId(),follow.getToUser())));
        return followDTOS;
    }
}
