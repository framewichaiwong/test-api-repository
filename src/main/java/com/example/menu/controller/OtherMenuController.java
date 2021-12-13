package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.OtherMenu;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.OtherMenuRepository;
import com.example.menu.service.OtherMenuService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/otherMenu")
public class OtherMenuController {

    @Autowired
    private OtherMenuRepository otherMenuRepository;

    @Autowired
    private OtherMenuService otherMenuService;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping(value = "/save")
    public Object otherMenuSave(OtherMenu otherMenu){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if (optUserManager.isPresent()){
            OtherMenu checkName = otherMenuRepository.findByOtherMenuName(otherMenu.getOtherMenuName());
            if (checkName == null){
                otherMenu.setManagerId(optUserManager.get().getManagerId()); /// Set manager_id
                OtherMenu insert = otherMenuService.otherMenuSave(otherMenu);
                if(insert != null){
                    response.setStatus(1);
                    response.setMessage("Save Success");
                    response.setData(insert);
                }
            }else {
                response.setStatus(0);
                response.setMessage("Can't Success");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @GetMapping(value = "/list")
    public Object getOtherMenu(){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            List<OtherMenu> lst = otherMenuService.otherMenuList(optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setMessage("OK");
            response.setData(lst);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping(value = "/delete/{otherMenuId}")
    public Object otherMenuDelete(@PathVariable int otherMenuId){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            Optional<OtherMenu> checkOtherMenu = otherMenuRepository.findById(otherMenuId);
            if(checkOtherMenu.isPresent()){
                otherMenuService.OtherMenuDelete(otherMenuId);
                response.setStatus(1);
                response.setMessage("Delete Success");
            }else {
                response.setStatus(0);
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping(value = "/update")
    public Object otherMenuUpdate(OtherMenu otherMenu){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            Optional<OtherMenu> findData = otherMenuRepository.findById(otherMenu.getOtherMenuId());
            if(findData.isPresent()){
                OtherMenu save = otherMenuService.otherMenuUpdate(otherMenu);
                response.setStatus(1);
                response.setMessage("Save Success");
                response.setData(save);
            }else {
                response.setStatus(0);
                response.setMessage("No OtherMenu");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }
}