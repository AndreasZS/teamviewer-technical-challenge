package com.teamviewer.technicalchallenge.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class Product extends RepresentationModel<Product> {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
