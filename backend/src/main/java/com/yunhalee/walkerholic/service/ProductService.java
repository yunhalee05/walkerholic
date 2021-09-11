package com.yunhalee.walkerholic.service;

import com.yunhalee.walkerholic.FileUploadUtils;
import com.yunhalee.walkerholic.dto.ProductCreateDTO;
import com.yunhalee.walkerholic.dto.ProductDTO;
import com.yunhalee.walkerholic.entity.Product;
import com.yunhalee.walkerholic.entity.ProductImage;
import com.yunhalee.walkerholic.entity.User;
import com.yunhalee.walkerholic.repository.ProductImageRepository;
import com.yunhalee.walkerholic.repository.ProductRepository;
import com.yunhalee.walkerholic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ProductImageRepository productImageRepository;

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
            existingProduct.setCategory(productCreateDTO.getCategory());
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
            product.setCategory(productCreateDTO.getCategory());
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

    public String deleteProduct(Integer id){
        Product product = productRepository.findByProductId(id);
        String dir = "/productUploads/"+ id;
        FileUploadUtils.deleteDir(dir);

        productRepository.deleteById(id);
        return "Product Deleted Successfully.";
    }

}
