package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "cancel_order_menu")
public class CancelOrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancel_id")
    private int cancelId;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @Column(name = "order_id")
    private int orderId;
}
