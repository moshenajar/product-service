package com.bignajar.products.repository;


import com.bignajar.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{'_id': ?0}")
    Optional<Product> findByDocumentId(String id);
}
