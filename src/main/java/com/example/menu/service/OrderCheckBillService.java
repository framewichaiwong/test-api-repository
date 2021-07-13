package com.example.menu.service;

import com.example.menu.entities.OrderCheckBill;
import com.example.menu.repository.OrderCheckBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class OrderCheckBillService {

    @Autowired
    private OrderCheckBillRepository orderCheckBillRepository;

    public OrderCheckBill saveOrderCheckBill(OrderCheckBill orderCheckBill) {
        return orderCheckBillRepository.save(orderCheckBill);
    }
}
