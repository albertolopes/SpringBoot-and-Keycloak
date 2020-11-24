package com.beto.food.dto;

import com.beto.food.entity.Address;
import com.beto.food.entity.User;
import com.beto.food.entity.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Profile profile;

    private String urlImage;

    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();
}