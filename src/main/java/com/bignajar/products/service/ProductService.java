package com.bignajar.products.service;

import com.bignajar.products.dto.ProductRequest;
import com.bignajar.products.dto.ProductResponse;
import com.bignajar.products.model.Product;
import com.bignajar.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .productSku(productRequest.getProductSku())
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productShortName(productRequest.getProductShortName())
                .ProductDescription(productRequest.getProductDescription())
                .createdDate(productRequest.getCreatedDate())
                .deliveryTimeSpan(productRequest.getDeliveryTimeSpan())
                .categoryId(productRequest.getCategoryId())
                .productImageUrl(productRequest.getProductImageUrl())
                .categoryName(productRequest.getCategoryName())
                .build();

        productRepository.save(product);
       // log.info("Product {} is saved", product.getId());
    }

    public void updateProduct(Product product)
    {
        try {
            Optional<Product> oProduct =  productRepository.findByDocumentId(product.getId());
            if(oProduct.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
            productRepository.save(product);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
        }

    }


    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse findById(String id) {
        Optional<Product> product =  productRepository.findByDocumentId(id);
        return product.map(this::mapToProductResponse).get();
    }

    public void delete(String id) {
        try {
           Optional<Product> product =  productRepository.findByDocumentId(id);
           productRepository.delete(product.get());
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
        }
    }


    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .Id(product.getId())
                .productSku(product.getProductSku())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productShortName(product.getProductShortName())
                .ProductDescription(product.getProductDescription())
                .createdDate(product.getCreatedDate())
                .deliveryTimeSpan(product.getDeliveryTimeSpan())
                .categoryId(product.getCategoryId())
                .productImageUrl(product.getProductImageUrl())
                .categoryName(product.getCategoryName())
                .build();
    }

}
