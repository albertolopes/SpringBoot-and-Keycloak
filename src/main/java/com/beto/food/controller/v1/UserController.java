package com.beto.food.controller.v1;

import com.beto.food.dto.UserDTO;
import com.beto.food.mapper.UserMapper;
import com.beto.food.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "User")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private final UserMapper mapper;

    @PostMapping
    @ApiOperation("Save a user.")
    public ResponseEntity<UserDTO> salvar(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.salvar(mapper.toEntity(dto))));
    }

    @PutMapping
    @ApiOperation("Update a user.")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(mapper.toDto(service.update(mapper.toEntity(dto))));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Update a user.")
    public ResponseEntity<UserDTO> findUserById(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Drop a user")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
