package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.OrderItem;
import com.yunhalee.walkerholic.repository.OrderItemRepository;
import com.yunhalee.walkerholic.service.OrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderItemServiceTests {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    public void updateQty() {
        //given
        Integer orderItemId = 1;
        Integer qty = 2;

        //when
        orderItemService.updateQty(orderItemId, qty);
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();

        //then
        assertEquals(orderItem.getQty(), qty);
    }

    @Test
    public void deleteOrderItem() {
        //given
        Integer orderItemId = 1;

        //when
        orderItemService.deleteOrderItem(orderItemId);

        //then
        assertNull(orderItemRepository.findById(orderItemId));
    }
}
