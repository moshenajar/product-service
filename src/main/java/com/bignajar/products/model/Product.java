package com.bignajar.products.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

//@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String productSku;
    private String productName;
    private BigDecimal productPrice;
    private String productShortName;
    private String ProductDescription;
    private LocalDateTime createdDate;
    private String deliveryTimeSpan;
    private Integer categoryId;
    private String productImageUrl;
    private String categoryName;
}