package com.beto.food.controller.v1;

import com.beto.food.dto.OrderDTO;
import com.beto.food.entity.Order;
import com.beto.food.mapper.OrderMapper;
import com.beto.food.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/Order")
@Api(tags = "Order")
@RequiredArgsConstructor
public class OrderController {

    public final OrderService service;

    public final OrderMapper mapper;

    @PostMapping
    @ApiOperation("Post an order")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.create(mapper.toEntity(dto))));
    }

    @PutMapping
    @ApiOperation("Update an order")
    public ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.update(mapper.toEntity(dto))));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Delete an order")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Find a order by id")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.find(id)));
    }
}
