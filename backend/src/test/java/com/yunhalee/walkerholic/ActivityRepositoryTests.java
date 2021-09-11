package com.yunhalee.walkerholic;


import com.yunhalee.walkerholic.entity.Activity;
import com.yunhalee.walkerholic.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ActivityRepositoryTests {

    @Autowired
    private ActivityRepository repo;

    @Test
    public void testCreateActivity(){
        Activity activity = new Activity("Plogging90", 100, "Picking up trash while jogging for more than 90minutes.");
        repo.save(activity);

    }


}
