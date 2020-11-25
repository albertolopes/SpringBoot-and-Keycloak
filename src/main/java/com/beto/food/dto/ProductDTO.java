package com.beto.food.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imgUrl;
}
