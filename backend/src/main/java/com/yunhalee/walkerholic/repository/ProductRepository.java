package com.yunhalee.walkerholic.repository;

import com.yunhalee.walkerholic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.productImages i LEFT JOIN FETCH p.user u LEFT JOIN FETCH p.reviews r LEFT JOIN FETCH r.user s WHERE p.id=:id")
    Product findByProductId(Integer id);

    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.productImages i LEFT JOIN FETCH p.user u LEFT JOIN FETCH p.reviews r LEFT JOIN FETCH r.user s WHERE u.id=:id")
    List<Product> findByUserId(Integer id);
}
