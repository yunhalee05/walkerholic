package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.dto.*;
import com.yunhalee.walkerholic.entity.*;
import com.yunhalee.walkerholic.repository.OrderItemRepository;
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
    private final OrderItemRepository orderItemRepository;

    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO){

        Address address = new Address(orderCreateDTO.getAddress().getName(),
                                        orderCreateDTO.getAddress().getCountry(),
                                        orderCreateDTO.getAddress().getCity(),
                                        orderCreateDTO.getAddress().getZipcode(),
                                        orderCreateDTO.getAddress().getAddress(),
                                        orderCreateDTO.getAddress().getLatitude(),
                                        orderCreateDTO.getAddress().getLongitude());

        User user = userRepository.findById(orderCreateDTO.getUserId()).get();

        List<OrderItemCreateDTO> orderItemCreateDTOS = orderCreateDTO.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemCreateDTOS.forEach(orderItemCreateDTO -> {
            Product product = productRepository.findById(orderItemCreateDTO.getProductId()).get();
            orderItems.add(OrderItem.createOrderItem(product, orderItemCreateDTO.getQty(), orderItemCreateDTO.getPrice()));
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

    public OrderCartDTO getCart(Integer id){
        Order order = orderRepository.findCartItemsByUserId(OrderStatus.CART,id);
        if(order==null){
            return new OrderCartDTO();
        }
        return new OrderCartDTO(order);
    }

    public Integer createCart(Integer id){
        Order order = new Order();
        User user = userRepository.findById(id).get();
        order.setOrderStatus(OrderStatus.CART);
        order.setUser(user);
        orderRepository.save(order);
        return order.getId();
    }

    public OrderItemDTO addToCart(Integer id, OrderItemCreateDTO orderItemCreateDTO){
        Order order = orderRepository.findById(id).get();
        Product product = productRepository.findById(orderItemCreateDTO.getProductId()).get();

        OrderItemDTO createdOrderItemDTO;
        if(orderItemCreateDTO.getId()==null){
            OrderItem orderItem = OrderItem.createOrderItem(product, orderItemCreateDTO.getQty(), orderItemCreateDTO.getPrice());
            orderItemRepository.save(orderItem);
            order.addOrderItem(orderItem);
            createdOrderItemDTO = new OrderItemDTO(orderItem);
        }else{
            OrderItem orderItem = orderItemRepository.findById(orderItemCreateDTO.getId()).get();
            orderItem.setQty(orderItemCreateDTO.getQty());
            orderItem.setPrice(orderItemCreateDTO.getPrice());
            orderItemRepository.save(orderItem);
            order.addOrderItem(orderItem);
            createdOrderItemDTO = new OrderItemDTO(orderItem);
        }

        orderRepository.save(order);
        return createdOrderItemDTO;
    }
}
