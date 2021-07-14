package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.OrderMenu;
import com.example.menu.repository.OrderMenuRepository;
import com.example.menu.service.OrderMenuService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderMenuController {

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    @Autowired
    private OrderMenuService orderMenuService;

    @PostMapping("/updateOrder/{orderId}")
    public Object updateOrder(@PathVariable int orderId,OrderMenu orderMenu) {
        APIResponse response = new APIResponse();
        Optional<OrderMenu> checkId = orderMenuRepository.findById(orderId);
        if(checkId.isPresent()) {
            OrderMenu order = orderMenuService.updateOrder(orderMenu);
            response.setStatus(1);
            response.setMessage("update success");
            response.setData(order);
        }else {
            response.setStatus(0);
            response.setMessage("can't update");
        }
        return  response;
    }

    @PostMapping("/deleteOrder/{orderId}")
    public Object deleteOrder(@PathVariable int orderId) {
        APIResponse response = new APIResponse();
        Optional<OrderMenu> checkId = orderMenuRepository.findById(orderId);
        if(checkId.isPresent()) {
            orderMenuService.deleteOrder(orderId);
            response.setStatus(1);
            response.setMessage("Delete Success");
        }else {
            response.setStatus(0);
            response.setMessage("Not Success");
        }
        return response;
    }

    @PostMapping("/deleteManagerIdNumberTable/{managerId}/{numberTable}")
    public Object deleteManagerIdNumberTable(@PathVariable int managerId, @PathVariable int numberTable) {
        APIResponse response = new APIResponse();
        List<OrderMenu> checkNumberTable = orderMenuRepository.findByManagerIdAndNumberTable(managerId,numberTable);
        if(checkNumberTable.isEmpty()){
            response.setStatus(0);
            response.setMessage("can't delete");
        }else{
            orderMenuService.deleteManagerIdNumberTable(checkNumberTable);
            response.setStatus(1);
            response.setMessage("delete success");
        }
        return response;
    }

    //Get All
    @GetMapping("/getOrder")
    public Object getOrder() {
        APIResponse response = new APIResponse();
        List<OrderMenu> listOrder = orderMenuService.getOrder();
        response.setData(listOrder);
        return response;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Set of customer

    @PostMapping("/saveOrder")
    public Object saveOrder(OrderMenu orderMenu) {
        APIResponse response = new APIResponse();
        OrderMenu inputOrder = orderMenuService.saveOrder(orderMenu);
        response.setStatus(1);
        response.setMessage("Save Success");
        response.setData(inputOrder);
        return response;
    }

    @GetMapping("/getOrderByManagerIdAndNumberTable/{managerId}/{numberTable}")
    public Object getOrderByManagerIdAndNumberTable(@PathVariable int managerId,@PathVariable int numberTable) {
        APIResponse response = new APIResponse();
        List<OrderMenu> getOrder = orderMenuService.getOrderByManagerIdAndNumberTable(managerId,numberTable);
        response.setData(getOrder);
        return response;
    }
}