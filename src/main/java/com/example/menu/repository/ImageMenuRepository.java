package com.example.menu.repository;

import com.example.menu.entities.ImageMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageMenuRepository extends JpaRepository<ImageMenu,Integer> {

    List<ImageMenu> findByManagerIdAndTypeMenuAndMenuId(int managerId, String typeMenu, int menuId);
    List<ImageMenu> findByMenuId(int menuId);
}
