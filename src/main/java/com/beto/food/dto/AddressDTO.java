package com.beto.food.dto;

import com.beto.food.entity.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AddressDTO {

    private Long id;

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    private String ddd;

    private User user;
}
