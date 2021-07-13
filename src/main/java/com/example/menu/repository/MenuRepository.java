package com.example.menu.repository;

import com.example.menu.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    List<Menu> findByManagerId(int managerId);
    List<Menu> findByManagerIdAndTypeMenu(int managerId,String typeMenu);
    Menu findByNameAndManagerId(String name,int managerId);
}
