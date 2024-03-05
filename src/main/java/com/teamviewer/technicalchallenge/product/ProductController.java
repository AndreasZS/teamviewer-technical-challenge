package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;


    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Get a list of all products.
     * @return ResponseEntity containing list of all products
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = this.productService.getProducts();
        return ResponseEntity.ok().body(products);
    }

    /**
     * Get a product by ID.
     * @param id Product ID
     * @return ResponseEntity containing product
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = this.productService.getProduct(id);
        return ResponseEntity.ok().body(product);
    }

    /**
     * Create a new product.
     * @param newProduct new Product to create
     * @return ResponseEntity containing product if it was created successfully
     */
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        Product createdProduct =  this.productService.createProduct(newProduct);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdProduct);
    }

    /**
     * Update an existing product.
     * @param newProduct new Product containing updated fields
     * @param id ID of product to update
     * @return ResponseEntity containing update product
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = this.productService.updateProduct(id, newProduct);
        return ResponseEntity.ok().body(updatedProduct);
    }

    /**
     * Delete a product by ID.
     * @param id ID of product to delete
     */
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }

}
