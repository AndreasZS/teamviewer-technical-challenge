package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final ProductModelAssembler assembler;

    @Autowired
    ProductController(ProductService productService, ProductModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping("/products")
    public CollectionModel<EntityModel<Product>> getAllProducts() {
//        List<EntityModel<Product>> products =
//                this.productService.getAllProducts().stream()
//                        .map(assembler::toModel)
//                        .toList();
//        return CollectionModel.of(products,
//                linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        List<Product> products = this.productService.getAllProducts();
        return assembler.toCollectionModel(products);
    }

    @GetMapping("/products/{id}")
    public EntityModel<Product> getProduct(@PathVariable Long id) {
        Product product = this.productService.getProduct(id).orElseThrow(() -> new ProductNotFoundException(id));
        return assembler.toModel(product);
    }

    @PostMapping("/products")
    public ResponseEntity<EntityModel<Product>> createProduct(Product newProduct) {
        EntityModel<Product> entityModel = assembler.toModel(this.productService.createProduct(newProduct));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        EntityModel<Product> entityModel = assembler.toModel(this.productService.updateProduct(id, newProduct));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/products/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }

}
