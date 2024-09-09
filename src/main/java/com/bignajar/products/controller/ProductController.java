package com.bignajar.products.controller;

import com.bignajar.products.dto.ProductRequest;
import com.bignajar.products.dto.ProductResponse;
import com.bignajar.products.model.Product;
import com.bignajar.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/create")
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}


	@DeleteMapping("/{id}")
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable("id") String id){
		productService.delete(id);
	}

	@PutMapping
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
	}

	@GetMapping
	@CrossOrigin
	@RequestMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse getProductById(@PathVariable("id") String id){
		return productService.findById(id);
	}

}

