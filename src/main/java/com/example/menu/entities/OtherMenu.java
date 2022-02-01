package com.example.menu.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Data
@Entity(name = "other_menu")
public class OtherMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "other_menu_id")
    private int otherMenuId;

    @Column(name = "other_menu_name")
    private String otherMenuName;

    @Column(name = "other_menu_price")
    private int otherMenuPrice;

    @Column(name = "other_selection")
    private String otherSelection;

    @Column(name = "other_status")
    private String otherStatus;

    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "type_menu")
    private String typeMenu;
}
