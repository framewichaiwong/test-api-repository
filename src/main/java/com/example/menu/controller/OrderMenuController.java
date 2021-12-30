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
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
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
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return  response;
    }

    @PostMapping("/deleteOrder/{orderId}")
    public Object deleteOrder(@PathVariable int orderId) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            Optional<OrderMenu> checkId = orderMenuRepository.findById(orderId);
            if(checkId.isPresent()) {
                orderMenuService.deleteOrder(orderId);
                response.setStatus(1);
                response.setMessage("Delete Success");
            }else {
                response.setStatus(0);
                response.setMessage("Not Success");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping("/deleteManagerIdNumberTable/{numberTable}")
    public Object deleteManagerIdNumberTable(@PathVariable int numberTable) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        if(optUserManager.isPresent()){
            List<OrderMenu> checkNumberTable = orderMenuRepository.findByManagerIdAndNumberTable(optUserManager.get().getManagerId(),numberTable);
            if(checkNumberTable.isEmpty()){
                response.setStatus(0);
                response.setMessage("can't delete");
            }else{
                orderMenuService.deleteManagerIdNumberTable(checkNumberTable);
                response.setStatus(1);
                response.setMessage("delete success");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    //Get All
    @GetMapping("/getOrder")
    public Object getOrder() {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2(); /// use token for pull user_member data.
        if (optUserManager.isPresent()) { /// UserManager
            List<OrderMenu> listOrder = orderMenuService.getOrder(optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setData(listOrder);
        }else if(optUserManagerMember.isPresent()){ /// UserManagerMember
            List<OrderMenu> listOrder = orderMenuService.getOrder(optUserManagerMember.get().getManagerId());
            response.setStatus(1);
            response.setData(listOrder);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
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

    @GetMapping("/getOrderByManagerIdAndNumberTable/{managerId}/{numberTable}")
    public Object getOrderByManagerIdAndNumberTable(@PathVariable int managerId,@PathVariable int numberTable) {
        APIResponse response = new APIResponse();
        List<OrderMenu> getOrder = orderMenuService.getOrderByManagerIdAndNumberTable(managerId,numberTable);
        response.setData(getOrder);
        return response;
    }
}