package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.CancelOrderMenu;
import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.CancelOrderMenuRepository;
import com.example.menu.service.CancelOrderMenuService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cancelOrderMenu")
public class CancelOrderMenuController {

    @Autowired
    private CancelOrderMenuRepository cancelOrderMenuRepository;

    @Autowired
    private CancelOrderMenuService cancelOrderMenuService;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping(value = "/save")
    public Object cancelOrderMenuSave(CancelOrderMenu cancelOrderMenu){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        Optional<UserManagerMember> optUserManagerMember = contextUtil.getUserDataFromContext2();
        CancelOrderMenu checkByOrderId = cancelOrderMenuRepository.findByOrderId(cancelOrderMenu.getOrderId());
        if(optUserManager.isPresent()){
            if(checkByOrderId == null){
                CancelOrderMenu cancelSave = cancelOrderMenuService.cancelOrderMenuSave(cancelOrderMenu);
                response.setStatus(1);
                response.setMessage("Save Success by user_manager");
                response.setData(cancelSave);
            }else{
                response.setStatus(0);
                response.setMessage("Can't Save");
            }
        }else if(optUserManagerMember.isPresent()){
            if(checkByOrderId == null){
                CancelOrderMenu cancelSave = cancelOrderMenuService.cancelOrderMenuSave(cancelOrderMenu);
                response.setStatus(1);
                response.setMessage("Save Success by user_manager_member");
                response.setData(cancelSave);
            }else{
                response.setStatus(0);
                response.setMessage("Can't Save");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    // For Customer.

    @GetMapping(value = "/list/{orderId}")
    public Object cancelOrderMenuList(@PathVariable int orderId){
        APIResponse response = new APIResponse();
        CancelOrderMenu checkByOrderId = cancelOrderMenuRepository.findByOrderId(orderId);
        if(checkByOrderId == null){
            response.setStatus(0);
            response.setMessage("No Data");
        }else{
            response.setStatus(1);
            response.setMessage("List Success");
            response.setData(checkByOrderId);
        }
        return response;
    }
}
