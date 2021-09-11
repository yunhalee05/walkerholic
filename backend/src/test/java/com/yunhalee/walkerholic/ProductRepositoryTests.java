package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.Product;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

@Rollback(false)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testGetProductById(){
        Integer id = 1;
        Product product = productRepository.findByProductId(id);

        assertThat(product.getUser().getFirstname(), is(equalTo("lee")));

    }
}
