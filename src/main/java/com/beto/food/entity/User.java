package com.beto.food.entity;

import com.beto.food.entity.enums.Profile;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_user")
    private Long id;

    @Column(name = "txt_name")
    private String name;

    @Column(name = "txt_email")
    private String email;

    @Column(name = "txt_password")
    private String password;

    @Column(name = "cod_profile")
    private Profile profile;

    @Column(name = "txt_image")
    private String urlImage;
}
