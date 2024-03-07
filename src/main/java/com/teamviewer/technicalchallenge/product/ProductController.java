package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<Product>> createProduct(ServerHttpRequest serverHttpRequest,
                                                       @RequestBody Product newProduct) {
        Product createdProduct =  this.productService.createProduct(newProduct);
        URI uri = serverHttpRequest.getURI().resolve("/%d".formatted(createdProduct.getId()));
        return Mono.just(ResponseEntity.created(uri).body(createdProduct));
    }

    /**
     * Update an existing product.
     * @param newProduct new Product containing updated fields
     * @param id ID of product to update
     * @return ResponseEntity containing update product
     */
    @PutMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = this.productService.updateProduct(id, newProduct);
        return Mono.just(ResponseEntity.ok().body(updatedProduct));
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
