package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.OrderMenu;
import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.OrderMenuRepository;
import com.example.menu.service.OrderMenuService;;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/order")
public class OrderMenuController {

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    @Autowired
    private OrderMenuService orderMenuService;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping("/updateOrder/{orderId}")
    public Object updateOrder(@PathVariable int orderId,OrderMenu orderMenu) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user_manager data.
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2(); /// use token for pull user_manager_member data.
        Optional<OrderMenu> checkId = orderMenuRepository.findById(orderId);
        if (optUserManager.isPresent()) {
            if (checkId.isPresent()){
                OrderMenu order = orderMenuService.updateOrder(orderMenu);
                response.setStatus(1);
                response.setMessage("update success by user_manager");
                response.setData(order);
            }else {
                response.setStatus(0);
                response.setMessage("can't update");
            }
        }else if(optUserManagerMember.isPresent()){
            if (checkId.isPresent()){
                OrderMenu order = orderMenuService.updateOrder(orderMenu);
                response.setStatus(1);
                response.setMessage("update success by user_manager_member");
                response.setData(order);
            }else {
                response.setStatus(0);
                response.setMessage("can't update");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return  response;
    }

    //Get All
    @GetMapping("/getOrder/{tableCheckBillId}")
    public Object getOrder(@PathVariable int tableCheckBillId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2(); /// use token for pull user_member data.
        if (optUserManager.isPresent()) { /// UserManager
            List<OrderMenu> listOrder = orderMenuService.getOrder(optUserManager.get().getManagerId(),tableCheckBillId);
            response.setStatus(1);
            response.setData(listOrder);
        }else if(optUserManagerMember.isPresent()){ /// UserManagerMember
            List<OrderMenu> listOrder = orderMenuService.getOrder(optUserManagerMember.get().getManagerId(),tableCheckBillId);
            response.setStatus(1);
            response.setData(listOrder);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    /// update for change (table_check_bill_id from 0 to table_check_bill_id).
    @PostMapping(value = "/orderUpdateTableCheckBillId/{managerId}/{numberTable}/{tableCheckBillId}")
    public Object orderUpdateTableCheckBillId(@PathVariable int managerId,@PathVariable int numberTable,@PathVariable int tableCheckBillId, OrderMenu orderMenu){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2();
        List<OrderMenu> listOrderMenu = orderMenuRepository.findByManagerIdAndNumberTableAndTableCheckBillId(managerId, numberTable, tableCheckBillId);
        if(optUserManager.isPresent()){
            for(OrderMenu order : listOrderMenu){
                orderMenu.setOrderId(order.getOrderId());
                orderMenu.setNumberMenu(order.getNumberMenu());
                orderMenu.setNumberTable(order.getNumberTable());
                orderMenu.setNameMenu(order.getNameMenu());
                orderMenu.setPriceMenu(order.getPriceMenu());
                orderMenu.setManagerId(order.getManagerId());
                orderMenu.setMakeStatus(order.getMakeStatus());
                orderMenuService.orderUpdateTableCheckBillId(orderMenu);
            }
            response.setStatus(1);
            response.setMessage("Update Success by user_manager");
        }else if(optUserManagerMember.isPresent()){
            for(OrderMenu order : listOrderMenu){
                orderMenu.setOrderId(order.getOrderId());
                orderMenu.setNumberMenu(order.getNumberMenu());
                orderMenu.setNumberTable(order.getNumberTable());
                orderMenu.setNameMenu(order.getNameMenu());
                orderMenu.setPriceMenu(order.getPriceMenu());
                orderMenu.setManagerId(order.getManagerId());
                orderMenu.setMakeStatus(order.getMakeStatus());
                orderMenuService.orderUpdateTableCheckBillId(orderMenu);
            }
            response.setStatus(1);
            response.setMessage("Update Success by user_manager_member");
        }else{
            response.setStatus(0);
            response.setMessage("No userManager");
        }
        return response;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Set of customer

    @PostMapping("/saveOrder")
    public Object saveOrder(OrderMenu orderMenu) {
        APIResponse response = new APIResponse();
        OrderMenu inputOrder = orderMenuService.saveOrder(orderMenu);
        if(inputOrder != null){
            response.setStatus(1);
            response.setMessage("Save Success");
            response.setData(inputOrder);
        }else{
            response.setStatus(0);
            response.setMessage("Can't Save");
        }
        return response;
    }

    @GetMapping("/getOrderByManagerIdAndNumberTableAndTableCheckBillId/{managerId}/{numberTable}/{tableCheckBillId}")
    public Object getOrderByManagerIdAndNumberTableAndTableCheckBillId(@PathVariable int managerId,@PathVariable int numberTable,@PathVariable int tableCheckBillId) {
        APIResponse response = new APIResponse();
        List<OrderMenu> getOrder = orderMenuService.getOrderByManagerIdAndNumberTableAndTableCheckBillId(managerId,numberTable,tableCheckBillId);
        response.setData(getOrder);
        return response;
    }
}