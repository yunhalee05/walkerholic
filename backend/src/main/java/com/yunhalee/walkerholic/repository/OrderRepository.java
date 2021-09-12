package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.user u LEFT JOIN FETCH o.orderItems i LEFT JOIN FETCH i.product p LEFT JOIN FETCH p.productImages m WHERE o.id=:id")
    Order findByOrderId(Integer id);
}
