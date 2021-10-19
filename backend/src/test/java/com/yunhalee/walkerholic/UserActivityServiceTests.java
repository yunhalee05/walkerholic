package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.dto.UserActivityCreateDTO;
import com.yunhalee.walkerholic.dto.UserActivityDTO;
import com.yunhalee.walkerholic.entity.Level;
import com.yunhalee.walkerholic.repository.UserActivityRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import com.yunhalee.walkerholic.service.UserActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserActivityServiceTests {

    @Autowired
    UserActivityService userActivityService;

    @Autowired
    UserActivityRepository userActivityRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUserActivity(){
        //given
        Integer activityId = 1;
        boolean finished = false;
        Integer userId = 1;
        UserActivityCreateDTO userActivityCreateDTO = new UserActivityCreateDTO(activityId, finished);

        //when
        HashMap<String, Object> response = userActivityService.saveUserActivity(userActivityCreateDTO, userId);
        UserActivityDTO userActivityDTO = (UserActivityDTO) response.get("activity");

        //then
        assertEquals(userActivityDTO.getActivityId(), activityId);
        assertEquals(userActivityDTO.isFinished(),finished);
    }

    @Test
    public void updateUserActivity(){
        //given
        Integer id = 1;
        Integer activityId = 1;
        boolean finished = true;
        Integer userId = 1;
        UserActivityCreateDTO userActivityCreateDTO = new UserActivityCreateDTO(id, activityId, finished);

        //when
        HashMap<String, Object> response = userActivityService.saveUserActivity(userActivityCreateDTO, userId);
        UserActivityDTO userActivityDTO = (UserActivityDTO) response.get("activity");

        //then
        assertEquals(userActivityDTO.getActivityId(), activityId);
        assertEquals(userActivityDTO.isFinished(),finished);
        assertEquals(userRepository.findById(userId).get().getLevel(), Level.Bronze);
    }

    @Test
    public void getUserActivitiesByUserId(){
        //given
        Integer page = 1;
        Integer userId = 1;

        //when
        HashMap<String, Object> response = userActivityService.getByUser(page, userId);
        List<UserActivityDTO> userActivityDTOS = (List<UserActivityDTO>) response.get("activities");

        //then
        for (UserActivityDTO userActivityDTO : userActivityDTOS) {
            assertEquals(userActivityRepository.findById(userActivityDTO.getId()).get().getUser().getId(), userId);
        }
    }

    @Test
    public void deleteUserActivity(){
        //given
        Integer id = 1;
        Integer userId = 1;

        //when
        userActivityService.deleteUserActivity(id, userId);

        //then
        assertNull(userActivityRepository.findById(id));
        assertEquals(userRepository.findById(userId).get().getLevel(), Level.Starter);
    }
}
