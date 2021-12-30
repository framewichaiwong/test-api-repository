package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.OrderOtherMenu;
import com.example.menu.repository.OrderOtherMenuRepository;
import com.example.menu.service.OrderOtherMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderOtherMenu")
public class OrderOtherMenuController {
    @Autowired
    private OrderOtherMenuRepository orderOtherMenuRepository;

    @Autowired
    private OrderOtherMenuService orderOtherMenuService;

    //------------------------------------------------------------------------------------------------------------------
    // Set of customer
    @PostMapping(value = "/save")
    public Object save(OrderOtherMenu orderOtherMenu){
        APIResponse response = new APIResponse();
        OrderOtherMenu oMenu = orderOtherMenuService.saveOrderOtherMenu(orderOtherMenu);
        if(oMenu != null){
            response.setStatus(1);
            response.setMessage("Save Success");
            response.setData(oMenu);
        }else {
            response.setStatus(0);
            response.setMessage("Can't Save");
        }
        return response;
    }

    @GetMapping(value = "/listForCustomer/{orderId}")
    public Object listForCustomer(@PathVariable int orderId){
        APIResponse response = new APIResponse();
        List<OrderOtherMenu> listOrderOtherMenu = orderOtherMenuService.listForCustomer(orderId);
        if(listOrderOtherMenu.isEmpty()){
            response.setStatus(0);
            response.setMessage("No Data");
        }else{
            response.setStatus(1);
            response.setMessage("List Success");
            response.setData(listOrderOtherMenu);
        }
        return response;
    }
}
