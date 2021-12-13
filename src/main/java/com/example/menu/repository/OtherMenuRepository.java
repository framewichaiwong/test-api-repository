package com.example.menu.repository;

import com.example.menu.entities.OtherMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtherMenuRepository extends JpaRepository<OtherMenu,Integer> {
    OtherMenu findByOtherMenuName(String otherMenuName);
    OtherMenu findByOtherMenuNameAndManagerId(String otherMenuName, int managerId);
    List<OtherMenu> findByManagerId(int managerId);
}
