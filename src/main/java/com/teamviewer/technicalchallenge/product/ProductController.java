package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        return this.productService.getProduct(id).orElseThrow();
    }

    @PostMapping
    public void createProduct(Product newProduct) {
        this.productService.createProduct(newProduct);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, Product newProduct) {
        this.productService.updateProduct(id, newProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable long id) {
        this.productService.deleteProduct(id);
    }

}
