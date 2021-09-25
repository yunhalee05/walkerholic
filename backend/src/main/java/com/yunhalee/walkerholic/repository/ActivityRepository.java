package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query(value = "SELECT DISTINCT a FROM Activity a LEFT JOIN FETCH a.userActivities s LEFT JOIN FETCH s.user u WHERE a.id=:id")
    Activity findByActivityId(Integer id);

}
