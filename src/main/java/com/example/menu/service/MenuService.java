package com.example.menu.service;

import com.example.menu.entities.Menu;
import com.example.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu save(Menu menu,int managerId) {
        menu.setManagerId(managerId); /// Set (managerId) into Table Menu.
        return menuRepository.save(menu);
    }

    public void update(Menu menu) {
        menuRepository.save(menu);
    }

    // Test update status
    /*public void updateStatusSale(int menuId, Menu menu) {
        menuRepository.setStatusSaleForMenu(menuId, menu.getStatusSale());
    }*/

    public void delete(int menuId) {
        menuRepository.deleteById(menuId);
    }

    /*public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }*/

    public List<Menu> listByManagerIdAndTypeMenu(int managerId,String typeMenu) {
        return menuRepository.findByManagerIdAndTypeMenu(managerId,typeMenu);
    }

    //------------------------------------------------------------------------------
    // Set of customer

    public List<Menu> listManagerIdAndTypeMenu(int managerId,String typeMenu) {
        return menuRepository.findByManagerIdAndTypeMenu(managerId,typeMenu);
    }
}
