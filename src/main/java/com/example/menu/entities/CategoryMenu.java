package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "category_menu")
public class CategoryMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_menu_id")
    private int categoryMenuId;

    @Column(name = "category_menu_name")
    private String categoryMenuName;

    @Column(name = "other_menu_id")
    private int otherMenuId;

    @Column(name = "manager_id")
    private int managerId;
}
