package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Address;
import com.yunhalee.walkerholic.entity.Order;
import com.yunhalee.walkerholic.entity.OrderItem;
import com.yunhalee.walkerholic.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderDTO {

    private Integer id;

    private String orderStatus;

    private boolean isPaid;

    private String paymentMethod;

    private LocalDateTime paidAt;

    private boolean isDelivered;

    private LocalDateTime deliveredAt;

    private AddressDTO address;

    private OrderUser user;

    private List<OrderItem> orderItems;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderStatus = order.getOrderStatus().name();
        this.isPaid = order.isPaid();
        this.paymentMethod = order.getPaymentMethod();
        this.paidAt = order.getPaidAt();
        this.isDelivered = order.isDelivered();
        this.deliveredAt = order.getDeliveredAt();
        this.address = new AddressDTO(order.getAddress());
        this.user = new OrderUser(order.getUser());
        this.orderItems = OrderItem.listItems(order.getOrderItems());
    }

    @Getter
    static class OrderUser{
        private Integer id;
        private String fullname;
        private String imageUrl;

        public OrderUser(User user) {
            this.id = user.getId();
            this.fullname = user.getFullname();
            this.imageUrl = user.getImageUrl();
        }
    }

    @Getter
    static class OrderItem{
        private Integer id;
        private Integer qty;
        private Integer price;

        private Integer productId;
        private String productName;
        private String productDescription;
        private String productBrand;
        private String productImageUrl;

        static List<OrderItem> listItems(Set<com.yunhalee.walkerholic.entity.OrderItem> orderItems){
            List<OrderItem> orderItemList = new ArrayList<>();
            orderItems.forEach(orderItem -> orderItemList.add(new OrderItem(orderItem)));
            return orderItemList;
        }

        public OrderItem(com.yunhalee.walkerholic.entity.OrderItem orderItem) {
            this.id = orderItem.getId();
            this.qty = orderItem.getQty();
            this.price = orderItem.getPrice();
            this.productId = orderItem.getProduct().getId();
            this.productName = orderItem.getProduct().getName();
            this.productDescription = orderItem.getProduct().getDescription();
            this.productBrand = orderItem.getProduct().getBrand();
            this.productImageUrl = orderItem.getProduct().getMainImageUrl();
        }
    }


}
