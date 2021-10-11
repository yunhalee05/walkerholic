package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.dto.*;
import com.yunhalee.walkerholic.entity.*;
import com.yunhalee.walkerholic.repository.OrderItemRepository;
import com.yunhalee.walkerholic.repository.OrderRepository;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public static final int ORDER_LIST_PER_PAGE = 10;


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
            orderItems.add(OrderItem.createOrderItem(product, orderItemCreateDTO.getQty()));
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
            OrderItem orderItem = OrderItem.createOrderItem(product, orderItemCreateDTO.getQty());
            orderItemRepository.save(orderItem);
            order.addOrderItem(orderItem);
            createdOrderItemDTO = new OrderItemDTO(orderItem);
        }else{
            OrderItem orderItem = orderItemRepository.findById(orderItemCreateDTO.getId()).get();
            orderItem.setQty(orderItemCreateDTO.getQty());
            orderItemRepository.save(orderItem);
            order.addOrderItem(orderItem);
            createdOrderItemDTO = new OrderItemDTO(orderItem);
        }

        orderRepository.save(order);
        return createdOrderItemDTO;
    }

    public HashMap<String, Object> getOrderList(Integer page){
        Pageable pageable = PageRequest.of(page-1,ORDER_LIST_PER_PAGE);
        Page<Order> orderPage = orderRepository.findAll(pageable, OrderStatus.CART);
        List<Order> orders = orderPage.getContent();
        List<OrderListDTO> orderListDTOS = new ArrayList<>();
        orders.forEach(order -> orderListDTOS.add(new OrderListDTO(order)));

        HashMap<String, Object> orderList = new HashMap<>();
        orderList.put("orders", orderListDTOS);
        orderList.put("totalElement", orderPage.getTotalElements());
        orderList.put("totalPage", orderPage.getTotalPages());

        return orderList;
    }

    public HashMap<String, Object> getOrderListBySeller(Integer page, Integer id){
        Pageable pageable = PageRequest.of(page-1,ORDER_LIST_PER_PAGE);
        Page<Order> orderPage = orderRepository.findBySellerId(pageable, id, OrderStatus.CART);
        List<Order> orders = orderPage.getContent();
        List<OrderListDTO> orderListDTOS = new ArrayList<>();
        orders.forEach(order -> orderListDTOS.add(new OrderListDTO(order)));

        HashMap<String, Object> orderList = new HashMap<>();
        orderList.put("orders", orderListDTOS);
        orderList.put("totalElement", orderPage.getTotalElements());
        orderList.put("totalPage", orderPage.getTotalPages());

        return orderList;
    }

    public void payOrder(OrderCreateDTO orderCreateDTO){
        Order order = orderRepository.findById(orderCreateDTO.getId()).get();
        Address address = new Address(orderCreateDTO.getAddress().getName(),orderCreateDTO.getAddress().getCountry(),orderCreateDTO.getAddress().getCity(), orderCreateDTO.getAddress().getZipcode(), orderCreateDTO.getAddress().getAddress());
        order.setAddress(address);
        order.setShipping(orderCreateDTO.getShipping());
        order.setPaymentMethod(orderCreateDTO.getPaymentMethod());
        order.setPaid(true);
        order.setPaidAt(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDER);
        orderRepository.save(order);
    }
}
