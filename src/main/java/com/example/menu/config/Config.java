package com.example.menu.config;

public class Config {

    public static final String[] ALLOW_API_PATH = new String[]{
            "/token_check",
            "/authorize",
            "/userManager/saveUserManager",

            "/userManager/listUser/{managerId}",
            "/menu/getMenu/{managerId}/{typeMenu}",
            "/order/saveOrder",
            "/order/getOrderByManagerIdAndNumberTable/{managerId}/{numberTable}",
            "/tableCheckBill/save",
            "/orderCheckBill/save",
            "/image/list/{managerId}/{menuId}/{typeMenu}",
            "/test/index"
    };

}