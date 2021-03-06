package com.yunhalee.walkerholic.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.yunhalee.walkerholic.RepositoryTest;
import com.yunhalee.walkerholic.orderitem.domain.OrderItem;
import com.yunhalee.walkerholic.product.domain.Category;
import com.yunhalee.walkerholic.product.domain.Product;
import com.yunhalee.walkerholic.productImage.domain.ProductImage;
import com.yunhalee.walkerholic.user.domain.User;
import com.yunhalee.walkerholic.user.domain.UserTest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class OrderRepositoryTests extends RepositoryTest {

    public static final int ORDER_LIST_PER_PAGE = 10;

    private User user;
    private User seller;
    private Order firstOrder;
    private Order secondOrder;
    private Order thirdOrder;
    private Order fourthOrder;
    private Order fifthOrder;
    private Product product;

    @Before
    public void setUp() {
        user = userRepository.save(UserTest.USER);
        seller = userRepository.save(UserTest.SELLER);
        product = productRepository.save(Product.of("first", "firstProduct", "test", Category.TUMBLER, 2, 2.00f, seller));
        ProductImage productImage = productImageRepository.save(ProductImage.of("first", "firstProduct", product));
        product.addProductImage(productImage);
        firstOrder = save(OrderStatus.ORDER, PaymentInfoTest.PAYMENT_INFO, DeliveryInfoTest.NOT_DELIVERED_DELIVERY, user.getId(), "2020-1-1 10:22");
        secondOrder = save(OrderStatus.CANCEL, PaymentInfoTest.PAYMENT_INFO, DeliveryInfoTest.NOT_DELIVERED_DELIVERY, user.getId(), "2020-1-3 10:22");
        thirdOrder = save(OrderStatus.ORDER, PaymentInfoTest.PAYMENT_INFO, DeliveryInfoTest.DELIVERED_DELIVERY, user.getId(), "2021-1-3 10:45");
        fourthOrder = save(OrderStatus.ORDER, PaymentInfoTest.PAYMENT_INFO, DeliveryInfoTest.NOT_DELIVERED_DELIVERY, seller.getId(), "2022-1-7 10:22");
        fifthOrder = save(OrderStatus.CANCEL, PaymentInfoTest.PAYMENT_INFO, DeliveryInfoTest.NOT_DELIVERED_DELIVERY, seller.getId(), "2022-1-10 10:22");
    }

    private Order save(OrderStatus orderStatus, Payment payment, Delivery deliveryInfo,
        Integer userId, String timeSeparator) {
        Order order = Order.builder()
            .orderStatus(orderStatus)
            .payment(payment)
            .delivery(deliveryInfo)
            .userId(userId)
            .orderItems(new OrderItems())
            .timeSeparator(timeSeparator).build();
        orderRepository.save(order);
        OrderItem orderItem = new OrderItem(10, product, order);
        orderItemRepository.save(orderItem);
        order.addOrderItem(orderItem);
        return order;
    }


    @Test
    public void find_by_order_id() {
        //given

        //when
        Order order = orderRepository.findByOrderId(firstOrder.getId());

        //then
        assertThat(order.getId()).isEqualTo(firstOrder.getId());
    }

    @Test
    public void find_by_seller_id() {
        //given
        Pageable pageable = PageRequest.of(0, ORDER_LIST_PER_PAGE);

        //when
        Page<Order> orderPage = orderRepository.findBySellerId(pageable, seller.getId());
        List<Order> orders = orderPage.getContent();

        //then
        assertThat(orders.equals(Arrays.asList(firstOrder, secondOrder, thirdOrder, fourthOrder, fifthOrder))).isTrue();
    }


    @Test
    public void find_by_user_id() {
        //given

        //when
        Pageable pageable = PageRequest.of(0, ORDER_LIST_PER_PAGE);
        Page<Order> orderPage = orderRepository.findByUserId(pageable, user.getId());
        List<Order> orders = orderPage.getContent();

        //then
        assertThat(orders.equals(Arrays.asList(firstOrder, secondOrder, thirdOrder))).isTrue();
    }

    @Test
    public void find_all_orders() {
        //given

        //when
        Pageable pageable = PageRequest.of(0, ORDER_LIST_PER_PAGE);
        Page<Order> orderPage = orderRepository.findAllOrders(pageable);
        List<Order> orders = orderPage.getContent();

        //then
        LocalDateTime createdAt = orders.get(0).getCreatedAt();
        for (Order order : orders) {
            assertThat(createdAt.isBefore(order.getCreatedAt()) || createdAt.isEqual(order.getCreatedAt())).isTrue();
            createdAt = order.getCreatedAt();
        }
    }

    @Test
    public void find_existence_by_created_at_between_from_to() {
        LocalDateTime from = LocalDateTime.now().minusSeconds(30);
        LocalDateTime to = LocalDateTime.now();
        assertThat(orderRepository.existsByCreatedAtBetweenAndUserId(from, to, user.getId())).isTrue();
    }

    @Test
    public void check_duplicated_order() {
        assertThatThrownBy(() -> save(OrderStatus.ORDER, PaymentInfoTest.PAYMENT_INFO, DeliveryInfoTest.NOT_DELIVERED_DELIVERY, user.getId(), "2020-1-1 10:22"))
            .isInstanceOf(DataIntegrityViolationException.class);
    }

}
