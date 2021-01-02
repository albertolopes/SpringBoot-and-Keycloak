package com.beto.food.service;

import com.beto.food.config.AuthenticationConfig;
import com.beto.food.entity.User;
import com.beto.food.exception.DataIntegrityException;
import com.beto.food.exception.ObjectAlreadyExistsException;
import com.beto.food.exception.ObjectNotFoundException;
import com.beto.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final AuthenticationConfig authenticationConfig;

    public User salvar(User user) {
        validateUserInsert(user);
        user.setId(null);
        return repository.save(user);
    }

    public User update(User user) {
        validateUserUpdate(user);
        return repository.save(validationPassword(user));
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("User not found. ID: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void validateUserInsert(User user){
        User name = repository.findByName(user.getName());
        if(name != null){
            throw new ObjectAlreadyExistsException("User already exists. User: " + name.getName());
        }

        User email = repository.findByEmail(user.getEmail());
        if(email != null){
            throw new ObjectAlreadyExistsException("Email already exists. Email: " + email.getEmail());
        }
    }

    public void validateUserUpdate(User user){
        User name = repository.findByName(user.getName());
        if(name != null && !name.getId().equals(user.getId())){
            throw new ObjectAlreadyExistsException("User already exists. User: " + name.getName());
        }

        User email = repository.findByEmail(user.getEmail());
        if(email != null && !email.getId().equals(user.getId())){
            throw new ObjectAlreadyExistsException("Email already exists. Email: " + email.getEmail());
        }
    }

    public User validationPassword(User user){
        Optional<User> passwordReturn = repository.findById(user.getId()).filter(
                u -> !u.getPassword().isEmpty()
        );

        if(passwordReturn.isPresent()){
            if(passwordReturn.get().getPassword().equals(user.getPassword())){
                user.setPassword(passwordReturn.get().getPassword());
                return user;
            } else {
                user.setPassword(user.getPassword());
                return user;
            }
        } else {
            throw new DataIntegrityException("Id can't exists. Id:" + user.getId());
        }
    }
}
