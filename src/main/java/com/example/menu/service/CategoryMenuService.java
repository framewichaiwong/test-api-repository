package com.example.menu.service;

import com.example.menu.entities.CategoryMenu;
import com.example.menu.repository.CategoryMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryMenuService {

    @Autowired
    private CategoryMenuRepository categoryMenuRepository;

    public CategoryMenu categoryMenuSave(CategoryMenu categoryMenu){
        return categoryMenuRepository.save(categoryMenu);
    }

    public List<CategoryMenu> listCategoryByOtherMenuId(int otherMenuId){
        return categoryMenuRepository.findByOtherMenuId(otherMenuId);
    }

    public void categoryMenuDelete(int categoryMenuId){
        categoryMenuRepository.deleteById(categoryMenuId);
    }

    public CategoryMenu categoryMenuUpdate(CategoryMenu categoryMenu){
        return categoryMenuRepository.save(categoryMenu);
    }
}
