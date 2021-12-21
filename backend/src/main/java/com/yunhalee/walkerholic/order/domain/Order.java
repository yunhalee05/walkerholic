package com.yunhalee.walkerholic.order.domain;

import com.yunhalee.walkerholic.common.domain.BaseTimeEntity;
import com.yunhalee.walkerholic.orderitem.domain.OrderItem;
import com.yunhalee.walkerholic.useractivity.domain.Address;
import com.yunhalee.walkerholic.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Float shipping;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "is_delivered")
    private boolean isDelivered;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @Transient
    public Float getTotalAmount() {
        Float totalAmount = 0f;
        for (OrderItem orderItem : this.orderItems) {
            totalAmount += orderItem.getProduct().getPrice() * orderItem.getQty();
        }
        return totalAmount;
    }

    //연관관계메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //비지니스 로직
    public static Order createOrder(User user, Address address, List<OrderItem> orderItems,
        String paymentMethod) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        orderItems.forEach(orderItem -> {
            order.addOrderItem(orderItem);
        });
        order.setOrderStatus(OrderStatus.ORDER);
        order.setPaymentMethod(paymentMethod);
        return order;
    }

    public void cancel() {
        if (isDelivered) {
            throw new IllegalStateException("Order Already Completed.");
        }
        this.setOrderStatus(OrderStatus.CANCEL);
        orderItems.forEach(orderItem -> {
            orderItem.cancel();
        });
    }

    public void deliver() {
        if (!isPaid) {
            throw new IllegalStateException("Order must be paid.");
        }
        this.setDelivered(true);
        this.setDeliveredAt(LocalDateTime.now());
    }

}