package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.FileUploadUtils;
import com.yunhalee.walkerholic.dto.ActivityCreateDTO;
import com.yunhalee.walkerholic.dto.ActivityDTO;
import com.yunhalee.walkerholic.entity.Activity;
import com.yunhalee.walkerholic.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    private void saveActivityImage(Activity activity,MultipartFile multipartFile, boolean isNew){
        try{
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "activityUploads/" + activity.getId();

            if (!isNew) {
                FileUploadUtils.cleanDir(uploadDir);
            }

            FileUploadUtils.saveFile(uploadDir,fileName,multipartFile);
            activity.setImageUrl("/activityUploads/" + activity.getId() + "/" + fileName);

            activityRepository.save(activity);

        }catch (IOException ex){
            new IOException("Could not save file : " + multipartFile.getOriginalFilename());
        }
    }

    public String saveActivity(ActivityCreateDTO activityCreateDTO, MultipartFile multipartFile){

        if(activityCreateDTO.getId()!=null){
            Activity existingActivity = activityRepository.findById(activityCreateDTO.getId()).get();
            existingActivity.setName(activityCreateDTO.getName());
            existingActivity.setDescription(activityCreateDTO.getDescription());
            existingActivity.setScore(activityCreateDTO.getScore());

            if(multipartFile!=null){
                saveActivityImage(existingActivity,multipartFile,false);
            }
        }else {
            Activity activity = new Activity();
            activity.setName(activityCreateDTO.getName());
            activity.setDescription(activityCreateDTO.getDescription());
            activity.setScore(activityCreateDTO.getScore());
            activityRepository.save(activity);

            if (multipartFile != null) {
                saveActivityImage(activity, multipartFile, true);
            }
        }
        return "Activity saved Successfully.";

    }

    public ActivityDTO getActivity(Integer id){
        Activity activity = activityRepository.findByActivityId(id);
        ActivityDTO activityDTO = new ActivityDTO(activity);

        return activityDTO;
    }

    public String deleteActivity(Integer id){
        String dir = "/activityUploads/"+ id;
        FileUploadUtils.deleteDir(dir);
        activityRepository.deleteById(id);
        return "Activity Deleted Successfully.";
    }

}
