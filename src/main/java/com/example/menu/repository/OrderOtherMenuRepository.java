package com.example.menu.repository;

import com.example.menu.entities.OrderOtherMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderOtherMenuRepository extends JpaRepository<OrderOtherMenu,Integer> {
    List<OrderOtherMenu> findByOrderId(int orderId);
}
