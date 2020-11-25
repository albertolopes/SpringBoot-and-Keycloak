package com.beto.food.controller.v1;

import com.beto.food.dto.ProductDTO;
import com.beto.food.mapper.ProductMapper;
import com.beto.food.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Api(tags = "Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final ProductMapper mapper;

    @PostMapping
    @ApiOperation("Create a product")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.create(mapper.toEntity(dto))));
    }

    @PutMapping
    @ApiOperation("Update a product")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.update(mapper.toEntity(dto))));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Delete a product")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation("Find all products")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Find a product by id")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }
}
