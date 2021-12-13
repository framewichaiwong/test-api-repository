package com.example.menu.repository;

import com.example.menu.entities.CategoryMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMenuRepository extends JpaRepository<CategoryMenu,Integer> {
    List<CategoryMenu> findByOtherMenuId(int otherMenuId);
}
