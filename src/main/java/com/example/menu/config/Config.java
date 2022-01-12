package com.example.menu.config;

public class Config {

    public static final String[] ALLOW_API_PATH = new String[]{
            "/token_check",
            "/authorize",
            "/userManager/saveUserManager",

            "/userManager/listUser/{managerId}",
            "/menu/getMenu/{managerId}/{typeMenu}",
            "/order/saveOrder",
            "/order/getOrderByManagerIdAndNumberTableAndTableCheckBillId/{managerId}/{numberTable}/{tableCheckBillId}",
            "/tableCheckBill/save",
            "/tableCheckBill/check/{paymentStatus}",
            "/orderCheckBill/save",
            "/image/list/{managerId}/{menuId}/{typeMenu}",
            "/test/index"

            ,"/userMember/saveUserMember",

            "/categoryMenu/list/{managerId}/{categoryMenuName}",
            "/otherMenu/list/{otherMenuId}",

            "/orderOtherMenu/save",
            "/orderOtherMenu/listForCustomer/{orderId}",

            "/cancelOrderMenu/list/{orderId}",

            "/imageSlipTransfer/save",
            "/imageSlipTransfer/list/{imageSlipIid}",
    };

}