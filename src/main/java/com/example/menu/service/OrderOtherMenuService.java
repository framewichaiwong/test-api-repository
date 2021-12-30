package com.example.menu.service;

import com.example.menu.entities.OrderOtherMenu;
import com.example.menu.repository.OrderOtherMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderOtherMenuService {

    @Autowired
    private OrderOtherMenuRepository orderOtherMenuRepository;

    public OrderOtherMenu saveOrderOtherMenu(OrderOtherMenu orderOtherMenu){
        return orderOtherMenuRepository.save(orderOtherMenu);
    }

    public List<OrderOtherMenu> listForCustomer(int orderId){
        return orderOtherMenuRepository.findByOrderId(orderId);
    }
}
