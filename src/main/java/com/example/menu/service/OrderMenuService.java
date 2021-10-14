package com.example.menu.service;

import com.example.menu.entities.OrderMenu;
import com.example.menu.repository.OrderMenuRepository;
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

    public void deleteOrder(int orderId) {
        orderMenuRepository.deleteById(orderId);
    }

    public void deleteManagerIdNumberTable(List<OrderMenu> lst) {
        orderMenuRepository.deleteAll(lst);
    }

    public List<OrderMenu> getOrderByManagerIdAndNumberTable(int managerId, int numberTable) {
        return orderMenuRepository.findByManagerIdAndNumberTable(managerId,numberTable);
    }

    public List<OrderMenu> getOrder(int managerId) {
        return orderMenuRepository.findByManagerId(managerId);
    }
}
