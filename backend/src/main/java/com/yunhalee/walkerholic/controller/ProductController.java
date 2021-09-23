package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.ProductCreateDTO;
import com.yunhalee.walkerholic.dto.ProductDTO;
import com.yunhalee.walkerholic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/save")
    public String createProduct(@RequestParam("id")Integer id,
                                @RequestParam("name")String name,
                                @RequestParam("description")String description,
                                @RequestParam("brand")String brand,
                                @RequestParam("category")String category,
                                @RequestParam("stock")Integer stock,
                                @RequestParam("price")Float price,
                                @RequestParam("userId")Integer userId,
                                @RequestParam("multipartFile")List<MultipartFile> multipartFiles){
        ProductCreateDTO productCreateDTO = new ProductCreateDTO(id, name, description,brand,category,stock,price,userId);
        return productService.saveProduct(productCreateDTO, multipartFiles);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id")String id){
        Integer productId = Integer.parseInt(id);
        return new ResponseEntity<ProductDTO>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/products/{page}")
    public ResponseEntity<?> getProducts(@PathVariable("page")String page, @RequestParam(value = "sort",required = false)String sort, @RequestParam(value = "category", required = false)String category, @RequestParam(value = "keyword",required = false)String keyword){
        Integer pageNumber = Integer.parseInt(page);
        return new ResponseEntity<HashMap>(productService.getProducts(pageNumber, sort, category, keyword),HttpStatus.OK);
    }

    @GetMapping("/products/seller/{id}/{page}")
    public ResponseEntity<?> getProductsBySeller(@PathVariable("id")String id, @PathVariable("page")String page, @RequestParam(value = "sort",required = false)String sort, @RequestParam(value = "category", required = false)String category, @RequestParam(value = "keyword",required = false)String keyword) {
        Integer sellerId = Integer.parseInt(id);
        Integer pageNumber = Integer.parseInt(page);
        return new ResponseEntity<HashMap>(productService.getProductsBySeller(sellerId,pageNumber,sort, category, keyword),HttpStatus.OK);

    }

    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id")String id){
        Integer productId = Integer.parseInt(id);
        return productService.deleteProduct(productId);
    }


}
