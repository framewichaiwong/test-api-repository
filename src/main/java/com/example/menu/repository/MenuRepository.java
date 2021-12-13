package com.example.menu.repository;

import com.example.menu.entities.Menu;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    List<Menu> findByManagerIdAndTypeMenu(int managerId,String typeMenu);
    Menu findByNameAndManagerId(String name,int managerId);

}
