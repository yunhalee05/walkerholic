package com.yunhalee.walkerholic;

import com.yunhalee.walkerholic.entity.Category;
import com.yunhalee.walkerholic.entity.Product;
import com.yunhalee.walkerholic.entity.ProductImage;
import com.yunhalee.walkerholic.repository.ProductImageRepository;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private ProductImageRepository productImageRepository;


    @Test
    public void testGetProductById(){
        Integer id = 1;
        Product product = productRepository.findByProductId(id);

        assertThat(product.getUser().getFirstname(), is(equalTo("lee")));

    }
    @Test
    public void testCreateProduct(){
        Product product = new Product();
        product.setPrice(10);
        product.setBrand("Adidas");
        product.setCategory(Category.CLOTHES);
        product.setName("Shirts");
        product.setStock(100);
        product.setDescription("Adidas Shirts");

        productRepository.save(product);

    }

    @Test
    public void testDeleteById(){
        Integer id = 1;
        productRepository.deleteById(id);
    }

    @Test
    public void testGetProductByPagination(){
        Pageable pageable = PageRequest.of(0, 9, Sort.by(Sort.Direction.DESC, "average"));
        Page<Product> productPage = productRepository.findAllByKeyword(pageable,"");
        List<Product> products= productPage.getContent();
        products.forEach(product -> product.getName());
    }

    @Test
    public void testUpdateProduct(){
        Integer id =4;
        Product product = productRepository.findByProductId(id);
        ProductImage productImage = new ProductImage();
        productImage.setName("다운로드.png");
        productImage.setFilePath("/productUploads/4/다운로드.png");
        productImage.setProduct(product);
        productImageRepository.save(productImage);



    }
}
