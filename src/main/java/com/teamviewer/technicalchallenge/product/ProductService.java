package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }


    public Optional<Product> getProduct(long id) {
        return this.productRepository.findById(id);
    }

    public void createProduct(Product newProduct) {
        this.productRepository.save(newProduct);
    }

    public void updateProduct(long id, Product newProduct) {
        this.productRepository.save(newProduct);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
