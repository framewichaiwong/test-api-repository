package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import com.example.menu.entities.CategoryMenu;
import com.example.menu.entities.UserManager;
import com.example.menu.repository.CategoryMenuRepository;
import com.example.menu.service.CategoryMenuService;
import com.example.menu.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categoryMenu")
public class CategoryMenuController {

    @Autowired
    private CategoryMenuRepository categoryMenuRepository;

    @Autowired
    private CategoryMenuService categoryMenuService;

    @Autowired
    private ContextUtil contextUtil;

    @PostMapping(value = "/save")
    public Object categoryMenuSave(CategoryMenu categoryMenu){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            CategoryMenu menu = categoryMenuService.categoryMenuSave(categoryMenu);
            response.setStatus(1);
            response.setMessage("Save Success");
            response.setData(menu);
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @GetMapping(value = "/list/{otherMenuId}")
    public Object categoryMenuList(@PathVariable int otherMenuId){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if (optUserManager.isPresent()){
            List<CategoryMenu> list = categoryMenuService.listCategoryByOtherMenuId(otherMenuId);
            if(list != null){
                response.setStatus(1);
                response.setMessage("List Success");
                response.setData(list);
            }else {
                response.setStatus(0);
                response.setMessage("No data category_menu");
            }
        }else {
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping(value = "/delete/{otherMenuId}")
    public Object categoryMenuDelete(@PathVariable int otherMenuId){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if(optUserManager.isPresent()){
            List<CategoryMenu> categoryMenu = categoryMenuRepository.findByOtherMenuId(otherMenuId);
            if(categoryMenu.isEmpty()){
                response.setStatus(0);
            }else {
                for (CategoryMenu category : categoryMenu){
                    categoryMenuService.categoryMenuDelete(category.getCategoryMenuId());
                }
                response.setStatus(1);
                response.setMessage("Delete Success");
            }
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }

    @PostMapping(value = "/update")
    public Object categoryMenuUpdate(CategoryMenu categoryMenu){
        APIResponse response = new APIResponse();
        Optional<UserManager> optUserManager = contextUtil.getUserDataFromContext();
        if (optUserManager.isPresent()){
            CategoryMenu update = categoryMenuService.categoryMenuUpdate(categoryMenu);
            response.setStatus(1);
            response.setMessage("Update Success");
            response.setData(update);
        }else{
            response.setStatus(0);
            response.setMessage("No UserManager");
        }
        return response;
    }
}
