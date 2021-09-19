package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.OrderItem;
import com.yunhalee.walkerholic.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Rollback(false)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class OrderItemRepositoryTests {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void testDeleteOrderItem(){
        orderItemRepository.deleteById(3);

    }
}
