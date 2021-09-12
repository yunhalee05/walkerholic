package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.dto.OrderCreateDTO;
import com.yunhalee.walkerholic.dto.OrderDTO;
import com.yunhalee.walkerholic.dto.OrderItemDTO;
import com.yunhalee.walkerholic.entity.*;
import com.yunhalee.walkerholic.repository.OrderRepository;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO){

        Address address = new Address(orderCreateDTO.getAddress().getName(),
                                        orderCreateDTO.getAddress().getCountry(),
                                        orderCreateDTO.getAddress().getCity(),
                                        orderCreateDTO.getAddress().getZipcode(),
                                        orderCreateDTO.getAddress().getAddress(),
                                        orderCreateDTO.getAddress().getLatitude(),
                                        orderCreateDTO.getAddress().getLongitude());

        User user = userRepository.findById(orderCreateDTO.getUserId()).get();

        List<OrderItemDTO> orderItemDTOS = orderCreateDTO.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemDTOS.forEach(orderItemDTO -> {
            Product product = productRepository.findById(orderItemDTO.getProductId()).get();
            orderItems.add(OrderItem.createOrderItem(product,orderItemDTO.getQty(),orderItemDTO.getPrice()));
        });

        Order order = Order.createOrder(user, address, orderItems, orderCreateDTO.getPaymentMethod());

        orderRepository.save(order);

        return new OrderDTO(order);
    }

    public String cancelOrder(Integer id){
        Order order = orderRepository.findById(id).get();
        order.cancel();

        return "Order Canceled Successfully.";
    }

    public OrderDTO getOrder(Integer id){
        Order order = orderRepository.findByOrderId(id);
        return new OrderDTO(order);
    }
}
