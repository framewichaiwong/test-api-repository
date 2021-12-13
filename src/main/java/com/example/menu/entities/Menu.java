package com.example.menu.entities;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@ToString
@DynamicUpdate
@Entity(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "category_menu")
    private String categoryMenu;

    @Column(name = "name")
    private String name;

    @Column(name = "price_menu_normal")
    private int priceMenuNormal;

    @Column(name = "price_menu_special")
    private int priceMenuSpecial;

    @Column(name = "price_menu_promotion")
    private int priceMenuPromotion;

    @Column(name = "type_menu")
    private String typeMenu;

    @Column(name = "status_sale")
    private String statusSale;

    // (FK) table --> "user_manager"
    @Column(name = "manager_id")
    private int managerId;

}