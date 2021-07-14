package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.Menu;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.MenuRepository;
import com.example.menu.service.MenuService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping("/saveMenu")
    public Object insertMenu(Menu menu) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        Menu checkName = menuRepository.findByNameAndManagerId(menu.getName(),optUserManager.get().getManagerId());
        if(checkName == null) {
            Menu insertMenu = menuService.save(menu, optUserManager.get().getManagerId());
            response.setStatus(1);
            response.setMessage("Save Success...");
            response.setData(insertMenu);
        }else{
            response.setStatus(0);
            response.setMessage("can't save...!!!");
        }
        return response;
    }

    @PostMapping("/updateMenu/{menuId}")
    public Object updateMenu(@PathVariable int menuId, Menu menu){
        APIResponse response = new APIResponse();
        Optional<Menu> checkMenuId = menuRepository.findById(menuId);
        if (checkMenuId.isPresent()) {
            menuService.update(menu);
            response.setStatus(1);
            response.setMessage("Success");
        }else {
            response.setStatus(0);
            response.setMessage("Can't Success");
        }
        return response;
    }

    @PostMapping("/deleteMenu/{menuId}")
    public Object deleteMenu(@PathVariable int menuId) {
        APIResponse response = new APIResponse();
        Optional<Menu> checkId = menuRepository.findById(menuId);
        if (checkId.isPresent()) {
            menuService.delete(menuId);
            response.setStatus(1);
            response.setMessage("Delete Success");
        }else {
            response.setStatus(0);
            response.setMessage("Not Success");
        }
        return response;
    }

    @GetMapping("/getMenu")
    public Object listMenu() {
        APIResponse response = new APIResponse();
        List<Menu> getMenu = menuService.getAllMenu();
        response.setData(getMenu);
        return response;
    }

    @GetMapping("/getMenu/{typeMenu}")
    public Object listMenuByManagerIdAndTypeMenu(@PathVariable String typeMenu) {
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext(); /// use token for pull user data.
        List<Menu> listMenu = menuService.listByManagerIdAndTypeMenu(optUserManager.get().getManagerId(),typeMenu);
        response.setData(listMenu);
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/getMenu/{managerId}/{typeMenu}")
    public Object listMenuByManagerId(@PathVariable int managerId,@PathVariable String typeMenu) {
        APIResponse response = new APIResponse();
        List<Menu> listMenu = menuService.listManagerIdAndTypeMenu(managerId,typeMenu);
        if(listMenu != null) {
            response.setStatus(1);
            response.setMessage("List Menu Success");
            response.setData(listMenu);
        }else {
            response.setStatus(0);
            response.setMessage("No Data");
        }
        return response;
    }
}