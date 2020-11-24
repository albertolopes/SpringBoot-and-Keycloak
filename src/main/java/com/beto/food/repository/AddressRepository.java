package com.beto.food.repository;

import com.beto.food.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query( "select a from Address a where a.user.id = :id")
    List<Address> findByUser(@Param("id") Long id);
}
