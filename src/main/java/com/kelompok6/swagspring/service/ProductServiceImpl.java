package com.kelompok6.swagspring.service;

import com.kelompok6.swagspring.exception.ResourceNotFoundException;
import com.kelompok6.swagspring.model.Product;
import com.kelompok6.swagspring.model.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
            return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> db = productRepository.findById(product.getId());

        if (db.isPresent()) {
            Product update = db.get();
            update.setId(product.getId());
            update.setName(product.getName());
            update.setPrice(product.getPrice());
            productRepository.save(update);
            return update;
        } else {
            throw  new ResourceNotFoundException("Record not found with id : " + product.getId());
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> db = productRepository.findById(productId);

        if (db.isPresent()) return db.get();
        else throw new ResourceNotFoundException("Record not found with id : " + productId);
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> db = productRepository.findById(id);

        if (db.isPresent()) productRepository.delete(db.get());
        else throw new ResourceNotFoundException("Record not found with id : " + id);

    }
}
