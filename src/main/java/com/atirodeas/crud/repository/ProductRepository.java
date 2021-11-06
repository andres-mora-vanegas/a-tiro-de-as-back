package com.atirodeas.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atirodeas.crud.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
