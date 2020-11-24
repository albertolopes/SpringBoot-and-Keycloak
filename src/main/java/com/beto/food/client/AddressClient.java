package com.beto.food.client;

import com.beto.food.entity.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "addressClient", url = "https://viacep.com.br/ws/")
public interface AddressClient {

    @GetMapping("{cep}/json/")
    Address getAddressByCep(@PathVariable String cep);
}
