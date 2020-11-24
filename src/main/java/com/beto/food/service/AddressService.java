package com.beto.food.service;

import com.beto.food.client.AddressClient;
import com.beto.food.entity.Address;
import com.beto.food.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    private final AddressClient client;

    public Address save(Address address){
        return repository.save(address);
    }

    public Address update(Address address) {
        return repository.save(address);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Address> getAddresById(Long id) {
        return repository.findByUser(id);
    }

    public Address getAddressByCep(String cep){
        return client.getAddressByCep(cep);
    }
}
