package com.beto.food.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_address")
    private Long id;

    @Column(name = "txt_cep")
    private String cep;

    @Column(name = "txt_logradouro")
    private String logradouro;

    @Column(name = "txt_complemento")
    private String complemento;

    @Column(name = "txt_bairro")
    private String bairro;

    @Column(name = "txt_localidade")
    private String localidade;

    @Column(name = "txt_uf")
    private String uf;

    @Column(name = "txt_ddd")
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "user_cod_user")
    private User user;
}
