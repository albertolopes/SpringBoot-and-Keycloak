package com.beto.food.dto;

import com.beto.food.entity.enums.Profile;
import lombok.*;

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
}