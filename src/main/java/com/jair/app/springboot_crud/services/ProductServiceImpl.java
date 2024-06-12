package com.jair.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jair.app.springboot_crud.entities.Product;
import com.jair.app.springboot_crud.repositories.ProductRespository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRespository respository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) respository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return respository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return respository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = respository.findById(id);

        if (optionalProduct.isPresent()) {
            Product productDB = optionalProduct.orElseThrow();

            productDB.setSku(product.getSku());
            productDB.setName(product.getName());
            productDB.setDescription(product.getDescription());
            productDB.setPrice(product.getPrice());

            return Optional.of(respository.save(productDB));
        }

        return optionalProduct;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = respository.findById(id);

        optionalProduct.ifPresent(productDB -> respository.delete(productDB));

        return optionalProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return respository.existsBySku(sku);
    }
}
