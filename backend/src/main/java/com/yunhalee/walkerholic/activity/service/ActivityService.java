package com.yunhalee.walkerholic.activity.service;

import com.yunhalee.walkerholic.activity.dto.ActivityRequest;
import com.yunhalee.walkerholic.activity.exception.ActivityNotFoundException;
import com.yunhalee.walkerholic.common.service.S3ImageUploader;
import com.yunhalee.walkerholic.activity.dto.ActivityResponse;
import com.yunhalee.walkerholic.activity.dto.ActivityDetailResponse;
import com.yunhalee.walkerholic.activity.domain.Activity;
import com.yunhalee.walkerholic.activity.domain.ActivityRepository;
import java.io.IOException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ActivityService {

    private ActivityRepository activityRepository;

    private S3ImageUploader s3ImageUploader;

    private static final String UPLOAD_DIR = "activity-uploads";

    @Value("${AWS_S3_BASE_IMAGE_URL}")
    private String defaultImageUrl;

    @Value("${AWS_S3_BUCKET_URL}")
    private String bucketUrl;

    public ActivityService(
        ActivityRepository activityRepository, S3ImageUploader s3ImageUploader) {
        this.activityRepository = activityRepository;
        this.s3ImageUploader = s3ImageUploader;
    }

    public ActivityResponse create(ActivityRequest activityRequest) {
        Activity activity = activityRequest.toActivity();
        activity.changeImageUrl(defaultImageUrl);
        activityRepository.save(activity);
        return new ActivityResponse(activity);
    }

    public ActivityResponse update(Integer id, ActivityRequest activityRequest) {
        Activity existingActivity = activityRepository.findById(id)
            .orElseThrow(() -> new ActivityNotFoundException(
                "Activity not found with id : " + id));
        Activity requestActivity = activityRequest.toActivity();
        Activity updatedActivity = existingActivity.update(requestActivity);
        return new ActivityResponse(updatedActivity);
    }

    @Transactional(readOnly = true)
    public ActivityDetailResponse activity(Integer id) {
        Activity activity = activityRepository.findByActivityId(id);
        ActivityDetailResponse activityDetailResponse = new ActivityDetailResponse(activity);

        return activityDetailResponse;
    }

    @Transactional(readOnly = true)
    public List<ActivityResponse> activities() {
        return activityRepository.findAll().stream()
            .map(ActivityResponse::new)
            .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        Activity activity = activityRepository.findById(id)
            .orElseThrow(() -> new ActivityNotFoundException(
                "Activity not found with id : " + id));
        s3ImageUploader.deleteFile(activity.getImageUrl());
        activityRepository.delete(activity);
        return;
    }

    public String uploadImage(MultipartFile multipartFile, Integer id)
        throws IOException {
        String imageUrl = s3ImageUploader.uploadFile(UPLOAD_DIR, multipartFile);
        Activity activity = activityRepository.findById(id)
            .orElseThrow(() -> new ActivityNotFoundException(
                "Activity not found with id : " + id));
        deleteOriginalImage(activity.getImageUrl());
        activity.changeImageUrl(imageUrl);
        return imageUrl;
    }

    private void deleteOriginalImage(String originalImageUrl) {
        if(!originalImageUrl.equals(defaultImageUrl)){
            s3ImageUploader.deleteFile(originalImageUrl.replaceAll(bucketUrl, ""));
        }
    }


}