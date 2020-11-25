package com.beto.food.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_product")
    private Long id;

    @Column(name = "txt_name")
    private String name;

    @Column(name = "txt_description")
    private String description;

    @Column(name = "dou_price")
    private Double price;

    @Column(name = "txt_img")
    private String imgUrl;
}
