package com.example.menu.repository;

import com.example.menu.entities.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu,Integer> {

    List<OrderMenu> findByManagerIdAndNumberTable(int managerId,int numberTable);
    List<OrderMenu> findByManagerId(int managerId);
}
