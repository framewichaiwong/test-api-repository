package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;

@Data
@ToString
@Entity(name = "user_manager")
public class UserManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "name")
    private String name;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "tel")
    private String tel;

    @Column(name = "name_restaurant")
    private String nameRestaurant;

    @Column(name = "number_table_total")
    private String numberTableTotal;

    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "picture")
    private String picture;
}