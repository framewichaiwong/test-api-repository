package com.example.menu.service;

import com.example.menu.entities.CancelOrderMenu;
import com.example.menu.repository.CancelOrderMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderMenuService {

    @Autowired
    private CancelOrderMenuRepository cancelOrderMenuRepository;

    public CancelOrderMenu cancelOrderMenuSave(CancelOrderMenu cancelOrderMenu){
        return cancelOrderMenuRepository.save(cancelOrderMenu);
    }
}
