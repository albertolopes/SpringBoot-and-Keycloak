package com.beto.food.dto;

import com.beto.food.entity.User;
import com.beto.food.entity.enums.OrderStatus;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDTO {

    private Long id;

    private Instant date;

    private OrderStatus status;

    private User customer;
}
