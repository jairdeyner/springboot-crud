package com.jair.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jair.app.springboot_crud.entities.Product;

public interface ProductRespository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
