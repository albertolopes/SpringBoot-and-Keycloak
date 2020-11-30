package com.beto.food.service;

import com.beto.food.entity.Order;
import com.beto.food.exception.ObjectNotFoundException;
import com.beto.food.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository repository;

    public Order create(Order order) {
        return repository.save(order);
    }

    public Order update(Order order){
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Order find(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Order not found: " + id));
    }
}
