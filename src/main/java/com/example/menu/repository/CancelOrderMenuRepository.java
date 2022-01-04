package com.example.menu.repository;

import com.example.menu.entities.CancelOrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelOrderMenuRepository extends JpaRepository<CancelOrderMenu,Integer> {
    CancelOrderMenu findByOrderId(int orderId);
}
