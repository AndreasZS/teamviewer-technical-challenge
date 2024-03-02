package com.teamviewer.technicalchallenge.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductModelAssembler assembler;

    @Autowired
    ProductController(ProductService productService, ProductModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping
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

    @GetMapping("/{id}")
    public EntityModel<Product> getProduct(@PathVariable Long id) {
        Product product = this.productService.getProduct(id).orElseThrow(() -> new ProductNotFoundException(id));
        return assembler.toModel(product);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(Product newProduct) {
        EntityModel<Product> entityModel = assembler.toModel(this.productService.createProduct(newProduct));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, Product newProduct) {
        EntityModel<Product> entityModel = assembler.toModel(this.productService.updateProduct(id, newProduct));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }

}
