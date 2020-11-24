package com.beto.food.controller.v1;

import com.beto.food.dto.AddressDTO;
import com.beto.food.mapper.AddressMapper;
import com.beto.food.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/address")
@Api(tags = "Address")
@RequiredArgsConstructor
public class AddressController{

    private final AddressService service;

    private final AddressMapper mapper;

    @PostMapping
    @ApiOperation("Save a address for an user")
    public ResponseEntity<AddressDTO> save(@Valid @RequestBody AddressDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.save(mapper.toEntity(dto))));
    }

    @PutMapping
    @ApiOperation("Update an user")
    public ResponseEntity<AddressDTO> update(@Valid @RequestBody AddressDTO dto){
        return ResponseEntity.ok(mapper.toDto(service.update(mapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an address")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Find address by user's id")
    public ResponseEntity<List<AddressDTO>> findUserAddress(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.getAddresById(id)));
    }

    @GetMapping(value = "/cep")
    @ApiOperation("Find address by cep.")
    public ResponseEntity<AddressDTO> findAddressByCep(@Valid @RequestParam("cep") String cep){
        return ResponseEntity.ok(mapper.toDto(service.getAddressByCep(cep)));
    }
}
