package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.FileUploadUtils;
import com.yunhalee.walkerholic.dto.ProductCreateDTO;
import com.yunhalee.walkerholic.dto.ProductDTO;
import com.yunhalee.walkerholic.dto.ProductListDTO;
import com.yunhalee.walkerholic.entity.Category;
import com.yunhalee.walkerholic.entity.Product;
import com.yunhalee.walkerholic.entity.ProductImage;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.ProductImageRepository;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ProductImageRepository productImageRepository;

    public static final int PRODUCT_PER_PAGE = 9;


    private void saveProductImage(Product product, List<MultipartFile> multipartFiles){
        multipartFiles.forEach(multipartFile -> {
            ProductImage productImage = new ProductImage();
            try{
                String fileName = System.currentTimeMillis() + StringUtils.cleanPath(multipartFile.getOriginalFilename());
                String uploadDir = "productUploads/" + product.getId();

                productImage.setName(fileName);
                FileUploadUtils.saveFile(uploadDir,fileName,multipartFile);
                productImage.setFilePath("/productUploads/" + product.getId() + "/" + fileName);
                productImage.setProduct(product);
                productImageRepository.save(productImage);

            }catch (IOException ex){
                new IOException("Could not save file : " + multipartFile.getOriginalFilename());
            }
        });

    }

    public String saveProduct(ProductCreateDTO productCreateDTO, List<MultipartFile> multipartFiles){

        if(productCreateDTO.getId()!=null){
            Product existingProduct = productRepository.findById(productCreateDTO.getId()).get();
            existingProduct.setName(productCreateDTO.getName());
            existingProduct.setDescription(productCreateDTO.getDescription());
            existingProduct.setBrand(productCreateDTO.getBrand());
            existingProduct.setCategory(Category.valueOf(productCreateDTO.getCategory()));
            existingProduct.setStock(productCreateDTO.getStock());
            existingProduct.setPrice(productCreateDTO.getPrice());

            if(multipartFiles.size()!=0){
                saveProductImage(existingProduct,multipartFiles);
            }

            productRepository.save(existingProduct);

        }else{
            Product product = new Product();
            User user = userRepository.findById(productCreateDTO.getUserId()).get();
            product.setName(productCreateDTO.getName());
            product.setDescription(productCreateDTO.getDescription());
            product.setBrand(productCreateDTO.getBrand());
            product.setCategory(Category.valueOf(productCreateDTO.getCategory()));
            product.setStock(productCreateDTO.getStock());
            product.setPrice(productCreateDTO.getPrice());
            product.setUser(user);

            productRepository.save(product);

            if (multipartFiles.size() != 0) {
                saveProductImage(product,multipartFiles);
            }

            productRepository.save(product);
        }

        return "Product Created Successfully.";
    }

    public ProductDTO getProduct(Integer id){
        Product product = productRepository.findByProductId(id);
        return new ProductDTO(product);
    }

    public HashMap<String,Object> getProducts(Integer page,String sort, String category, String keyword){

        Pageable pageable = PageRequest.of(page-1, PRODUCT_PER_PAGE, Sort.by("createdAt"));
        System.out.println(sort);
        if(sort.equals("highest")){
            pageable = PageRequest.of(page-1,PRODUCT_PER_PAGE, Sort.by(Sort.Direction.DESC,"price"));
        }else if(sort.equals("lowest")){
            pageable = PageRequest.of(page-1, PRODUCT_PER_PAGE, Sort.by("price"));
        }else if(sort.equals("toprated")){
            pageable = PageRequest.of(page-1, PRODUCT_PER_PAGE, Sort.by(Sort.Direction.DESC, "average"));
        }

        List<ProductListDTO> productDTOS = new ArrayList<>();
        HashMap<String, Object> productInfo = new HashMap<>();


        Page<Product> productPage;
        if(category == null  ||  category.isBlank()){
            productPage = productRepository.findAllByKeyword(pageable, keyword);
            List<Product> products = productPage.getContent();
            products.forEach(product -> productDTOS.add(new ProductListDTO(product)));
        }else{
            Category category1 = Category.valueOf(category);
            productPage = productRepository.findAllByCategory(pageable, category1, keyword);
            List<Product> products = productPage.getContent();
            products.forEach(product -> productDTOS.add(new ProductListDTO(product)));
        }
        productInfo.put("products",productDTOS);
        productInfo.put("totalElement", productPage.getTotalElements());
        productInfo.put("totalPage", productPage.getTotalPages());
        return productInfo;
    }

    public String deleteProduct(Integer id){
        Product product = productRepository.findByProductId(id);
        String dir = "/productUploads/"+ id;
        FileUploadUtils.deleteDir(dir);

        productRepository.deleteById(id);
        return "Product Deleted Successfully.";
    }

}
