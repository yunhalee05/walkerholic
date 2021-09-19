package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.Order;
import com.yunhalee.walkerholic.entity.OrderStatus;
import com.yunhalee.walkerholic.repository.OrderRepository;
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
public class OrderRepositoryTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testGetByUserId(){
        Integer id = 4;
        Order order = orderRepository.findCartItemsByUserId(OrderStatus.CART,id);
        System.out.println(order.getId());

    }
}
