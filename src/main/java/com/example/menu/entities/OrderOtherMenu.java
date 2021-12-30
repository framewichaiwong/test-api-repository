package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "order_other_menu")
public class OrderOtherMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_other_id")
    private int orderOtherId;

    @Column(name = "order_other_name")
    private String orderOtherName;

    @Column(name = "order_other_price")
    private int orderOtherPrice;

    @Column(name = "order_id")
    private int orderId;

//    @Column(name = "manager_id")
//    private int managerId;
//
//    @Column(name = "type_menu")
//    private String typeMenu;
}
