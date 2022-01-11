package com.example.menu.service;

import com.example.menu.entities.OrderMenu;
import com.example.menu.repository.OrderMenuRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderMenuService {

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    public OrderMenu saveOrder(OrderMenu order) {
        return orderMenuRepository.save(order);
    }

    public OrderMenu updateOrder(OrderMenu orderMenu) {
        return  orderMenuRepository.save(orderMenu);
    }

    public List<OrderMenu> getOrderByManagerIdAndNumberTableAndTableCheckBillId(int managerId, int numberTable, int tableCheckBillId) {
        return orderMenuRepository.findByManagerIdAndNumberTableAndTableCheckBillId(managerId,numberTable,tableCheckBillId);
    }

    public List<OrderMenu> getOrder(int managerId, int tableCheckBillId) {
        return orderMenuRepository.findByManagerIdAndTableCheckBillId(managerId,tableCheckBillId);
    }

    public void orderUpdateTableCheckBillId(OrderMenu orderMenu) {
        orderMenuRepository.save(orderMenu);
    }
}
