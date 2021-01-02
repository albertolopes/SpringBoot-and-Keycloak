package com.beto.food.repository;

import com.beto.food.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    User findByName(String name);
    
    @Transactional
    User findByEmail(String email);
}
