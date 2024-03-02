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


    public Optional<Product> getProduct(Long id) {
        return this.productRepository.findById(id);
    }

    public Product createProduct(Product newProduct) {
        return this.productRepository.save(newProduct);
    }

    public Product updateProduct(Long id, Product newProduct) {
        Product product = this.productRepository.findById(id).orElseGet(() -> newProduct);
        product.setId(id);
        product.setName(newProduct.getName());
        return this.productRepository.save(newProduct);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
