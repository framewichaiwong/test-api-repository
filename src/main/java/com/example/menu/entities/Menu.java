package com.example.menu.entities;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @Lob
    @Basic(fetch = FetchType.LAZY) //ดึงทันที
    @Column(name = "picture")
    private String picture;

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

    //table --> "user_manager"
    @Column(name = "manager_id")
    private int managerId;

}