package com.beto.food.service;

import com.beto.food.entity.Product;
import com.beto.food.exception.ObjectNotFoundException;
import com.beto.food.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Product product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Product not found: " + id));
    }
}
