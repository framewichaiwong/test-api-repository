package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.OrderCheckBill;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.OrderCheckBillRepository;
import com.example.menu.service.OrderCheckBillService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderCheckBill")
public class OrderCheckBillController {

    @Autowired
    private OrderCheckBillRepository orderCheckBillRepository;

    @Autowired
    private OrderCheckBillService orderCheckBillService;


    @PostMapping("/save")
    public Object saveOrderCheckBill(OrderCheckBill orderCheckBill) {
        APIResponse response = new APIResponse();
        OrderCheckBill saveOrderCheckBill = orderCheckBillService.saveOrderCheckBill(orderCheckBill);
        response.setStatus(1);
        response.setMessage("save success");
        response.setData(saveOrderCheckBill);
        return response;
    }
}
