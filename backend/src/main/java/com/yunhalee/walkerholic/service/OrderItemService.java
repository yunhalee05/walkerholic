package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.entity.OrderItem;
import com.yunhalee.walkerholic.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
//
//    private final OrderRepository orderRepository;
//
//    public List<OrderItemDTO> addToCart(Integer id, List<OrderItemDTO> orderItemDTOS){
//       Order order = orderRepository.findById(id).get();
//
//       orderItemDTOS.forEach(orderItemDTO -> {
//           OrderItem orderItem = new OrderItem()
//       });
//
//    }

    public void updateQty(Integer id, Integer qty){
        OrderItem orderItem = orderItemRepository.findById(id).get();
        orderItem.setQty(qty);
        orderItemRepository.save(orderItem);
        return ;
    }

    public void deleteOrderItem(Integer id){
        orderItemRepository.deleteById(id);
        return ;
    }
}
