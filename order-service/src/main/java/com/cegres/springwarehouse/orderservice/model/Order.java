package com.cegres.springwarehouse.orderservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product_order")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "address")
    String address;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;
}
