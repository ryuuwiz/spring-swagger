package com.kelompok6.swagspring.controller;

import com.kelompok6.swagspring.model.Product;
import com.kelompok6.swagspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok().body(service.getAllProduct());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.ok().body(service.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(service.createProduct(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok().body(service.updateProduct(product));
    }

    @DeleteMapping("/products/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) {
        service.deleteProduct(id);
        return HttpStatus.OK;
    }
}
