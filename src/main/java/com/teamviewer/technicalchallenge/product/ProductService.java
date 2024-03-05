package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product newProduct) {
        boolean productExists = this.productRepository.existsById(newProduct.getId());
        if (!productExists) {
            return this.productRepository.save(newProduct);
        } else {
            throw new ExistingProductException(newProduct.getId());
        }
    }

    public Product updateProduct(Long id, Product newProduct) {
        return this.productRepository.findById(id)
                .map(product -> {
                    product.setId(id);
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    return this.productRepository.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));

    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
