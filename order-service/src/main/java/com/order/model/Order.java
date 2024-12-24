package com.order.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderNumber;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<OrderLineItems> orderLineItemsList;
}
